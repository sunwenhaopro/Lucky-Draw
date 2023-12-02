<script setup lang="ts">
import * as echarts from 'echarts';
import 'echarts-gl'
import MapJson from '@/assets/china.json'
import { onMounted, reactive, ref } from 'vue';

interface RecordInfo {
  id: number,
  awardName: string,
  prizeName: string,
  isDraw: boolean,
  activityName: string
}

interface ExResult {
  time: string,
  description: string,
  path: string
}

interface MessageData {
  type: number
  data: any
}
const mediaGaugeData = ref([
  {
    value: 40,
    name: 'PC',
    title: {
      show: false,
      offsetCenter: ['0%', '-30%']
    },
    detail: {
      valueAnimation: true,
      offsetCenter: ['0%', '-20%']
    }
  },
  {
    value: 60,
    name: 'Mobie',
    title: {
      show: false,
      offsetCenter: ['0%', '0%']
    },
    detail: {
      valueAnimation: true,
      offsetCenter: ['0%', '10%']
    }
  }
])
enum Res {
  DrawResult = 1,
  ExceptionInfo = 2,
  RankInfo = 3,
  AccessTrends = 4,
  NewUserNumber = 5,
  RecordInfo = 6,
  Gnote = 7
}
interface Gnote {
  x: number,
  y: number
}
type EChartsOption = echarts.EChartsOption;
let accessTrendData: any   
const onlineNumber = reactive<string[]>(['0', '0', ',', '0', '0', '0', ',', '0', '0', '0'])
const activeNumber = reactive<string[]>(['0', '0', ',', '0', '0', '0', ',', '0', '0', '0'])
const numberItem1 = ref()
const numberItem2 = ref()

const mapContainer = ref()
const visitTrendsContainer = ref()
const newUserContainer = ref()
const comparePieContainer = ref()
const interfaceTopContainer = ref()
const mediaContainer = ref()
const browserContainer = ref()

const newUser = reactive([{ value: 0 }])
const todayInfo = ref({ uv: 0, pv: 0, error: 0, exception: 0, warn: 0 })
let browser: any[] = []
const result = reactive([
  { value: 0, name: 'Success' },
  { value: 0, name: 'Failure' }])
const drawResultData = ref<RecordInfo[]>([])
const exResultData = ref<ExResult[]>([])
let interfaceRankX: any
let interfaceRankY: number[];
let gnote: any
const areaRank = ref({ city: [], country: [], province: [] })
const eventSource = ref(null as EventSource | null);
const chartMap=new Map<string,echarts.ECharts>()
const optionMap=new Map<string,echarts.EChartsOption>()

onMounted(() => {
  eventSource.value = new EventSource("http://localhost:7216/monitor/sse/5");
  eventSource.value.addEventListener("message", (event) => {
    const data = JSON.parse(event.data) as MessageData;
    updateData(data)
  });
  initMap()
  initAccessTrends()
})

const updateData = (res: MessageData) => {
  switch (res.type) {
    case Res.NewUserNumber:
      newUser[0].value = res.data[0];
      initShowNumber(res.data[2], res.data[1])
      initNewUser()
      break;
    case Res.RecordInfo:
      convertRecordInfo(res)
      initBrowser()
      initCompare()
      initMedia()
      break;
    case Res.RankInfo:
      convertRankInfo(res)
      initInterfaceTop()
      break;
    case Res.Gnote:
      convertGeo(res)
      initMap()
      break;
    case Res.AccessTrends:
      convertAccessTrends(res)
      initAccessTrends()
      break;
    case Res.DrawResult:
      convertDrawResult(res)
      break;
    case Res.ExceptionInfo:
      convertExResult(res)
      break;
  }
}
const convertExResult = (res: MessageData) => {
  exResultData.value.push(res.data)
  if (exResultData.value.length > 20) {
    exResultData.value.shift()
  }
}
const convertDrawResult = (res: MessageData) => {
  drawResultData.value.push(res.data)
  if (drawResultData.value.length > 20) {
    drawResultData.value.shift()
  }
}
const convertAccessTrends = (res: MessageData) => {
  accessTrendData = Object.entries(res.data).map(([key, value]) => [Number(key), Number(value)]);
}
const convertGeo = (res: MessageData) => {
  gnote = res.data.map((item: any) => [item.x, item.y]);
}

const convertRecordInfo = (res: MessageData) => {
  mediaGaugeData.value[0].value = res.data.mediaMap.PC / (res.data.mediaMap.PC + res.data.mediaMap.Mobile) * 100
  mediaGaugeData.value[1].value = 100 - mediaGaugeData.value[0].value
  result[0].value = res.data.requestStatusMap.success
  result[1].value = res.data.requestStatusMap.fail
  todayInfo.value = res.data.statusMap
  browser.length = 0
  Object.keys(res.data.browserMap).map(key => { browser.push({ value: res.data.browserMap[key], name: key }) })
  browser.sort((a, b) => a.value - b.value)
}
const convertRankInfo = (res: MessageData) => {
  interfaceRankX = Object.keys(res.data.interfaceRank);
  interfaceRankY = Object.values(res.data.interfaceRank);
  areaRank.value.city = res.data.cityRank
  areaRank.value.country = res.data.countryRank
  areaRank.value.province = res.data.provinceRank
}

const initBrowser = () => {
  if(chartMap.has("browserEchart"))
  {
    let chart=chartMap.get("browserEchart")!
    let option=optionMap.get("browserEchart")!
    chart.setOption(option)
    return 
  }
  let browserEchart = echarts.init(browserContainer.value)
  chartMap.set("browserEchart",browserEchart)
  let option: EChartsOption = {
    title: {
      text: '浏览器类型',
      textStyle: {
        color: '#81D8CF',
      }
    },
    tooltip: {
      trigger: 'item'
    },
    visualMap: {
      show: false,
      min: 80,
      max: 600,
      inRange: {
        colorLightness: [0, 1]
      }
    },
    series: [
      {
        name: '浏览器类型',
        type: 'pie',
        radius: '55%',
        center: ['50%', '50%'],
        data: browser,
        roseType: 'radius',
        label: {
          color: 'RGBA(251,210,106,0.7)'
        },
        labelLine: {
          lineStyle: {
            color: 'rgba(255, 255, 255, 0.3)'
          },
          smooth: 0.2,
          length: 10,
          length2: 20
        },
        itemStyle: {
          color: '#c23531',
          shadowBlur: 200,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        },
        animationType: 'scale',
        animationEasing: 'elasticOut',
        animationDelay: function (idx) {
          return Math.random() * 200;
        }
      }
    ]
  }
  optionMap.set("browserEchart",option)
  option && browserEchart.setOption(option);
}

const initMedia = () => {
  if(chartMap.has("mediaEchart"))
  {
    let chart=chartMap.get("mediaEchart")!
    let option=optionMap.get("mediaEchart")!
    chart.setOption(option)
    return 
  }
  let mediaEchart = echarts.init(mediaContainer.value)
  chartMap.set("mediaEchart",mediaEchart)
  let option: EChartsOption = {
    backgroundColor: 'transparent',
    title: [
      {
        text: '登录客户端',
        textStyle: {
          color: '#81D8CF',
        }
      }
    ],
    tooltip: {
      trigger: 'item'
    },
    series: [
      {
        type: 'gauge',
        startAngle: 90,
        endAngle: -270,
        pointer: {
          show: false
        },
        progress: {
          show: true,
          overlap: false,
          roundCap: true,
          clip: false,
          itemStyle: {
            borderWidth: 1,
            borderColor: '#464646'
          }
        },
        axisLine: {
          show: true,
          lineStyle: {
            width: 20,
            color: '#071534'
          }
        },
        splitLine: {
          show: true,
          distance: 0,
          length: 10
        },
        axisTick: {
          show: true
        },
        axisLabel: {
          show: false,
          distance: 50
        },
        data: mediaGaugeData.value,
        title: {
          fontSize: 10
        },
        detail: {
          width: 50,
          height: 14,
          fontSize: 14,
          color: 'inherit',
          borderColor: 'inherit',
          borderRadius: 20,
          borderWidth: 1,
          formatter: '{value}%',
          backgroundColor: 'transparent'
        }
      }
    ]
  };
  optionMap.set("mediaEchart",option)
  option && mediaEchart.setOption(option)
}

const initInterfaceTop = () => {
  if(chartMap.has("interfaceTopEchart"))
  {
    let chart=chartMap.get("interfaceTopEchart")!
    let option=optionMap.get("interfaceTopEchart")!
    chart.setOption(option)
    return 
  }

  let interfaceTopEchart = echarts.init(interfaceTopContainer.value)
  chartMap.set("interfaceTopEchart",interfaceTopEchart)
  let option: EChartsOption = {
    title: {
      subtext: '接口访问TOP7',
      subtextStyle: {
        color: ' #81D8CF'
      },
      left: 'center',
    },
    xAxis: {
      max: 'dataMax'
    },
    yAxis: {
      type: 'category',
      data: interfaceRankX,
      inverse: true,
      animationDuration: 300,
      animationDurationUpdate: 300,
      max: interfaceRankX.length
    },
    series: [
      {
        realtimeSort: true,

        type: 'bar',
        data: interfaceRankY,
        label: {
          show: true,
          position: 'right',
          valueAnimation: true
        }
      }
    ],
    legend: {
      show: true
    },
    animationDuration: 0,
    animationDurationUpdate: 3000,
    animationEasing: 'linear',
    animationEasingUpdate: 'linear'
  };
  optionMap.set("interfaceTopEchart",option)
  option &&interfaceTopEchart.setOption(option)
}

const initNewUser = () => {
  if(chartMap.has("newUserEchart"))
  {
    let chart=chartMap.get("newUserEchart")!
    let option=optionMap.get("newUserEchart")!
    chart.setOption(option)
    return 
  }

  let newUserEchart = echarts.init(newUserContainer.value)
  chartMap.set("newUserEchart",newUserEchart)
  let option: EChartsOption = {
    title: {
      subtext: '今日新增用户',
      subtextStyle: {
        color: ' #81D8CF'
      },
      left: 'center'
    },
    series: [
      {
        type: 'gauge',
        progress: {
          show: true,
          width: 30
        },
        axisLine: {
          lineStyle: {
            width: 30
          }
        },
        axisTick: {
          show: false
        },
        splitLine: {
          length: 0,
        },
        axisLabel: {
          distance: 25,
          color: 'gold',
          fontSize: 13
        },
        anchor: {
          show: true,
          showAbove: true,
          size: 20,
          itemStyle: {
            borderWidth: 10
          }
        },
        title: {
          show: false
        },
        detail: {
          valueAnimation: true,
          fontSize: 30,
          offsetCenter: [0, '60%'],
          color: '#81D8CF'
        },
        data: newUser
      }
    ]
  }
  optionMap.set("newUserEchart",option)
  option && newUserEchart.setOption(option)
}

const initCompare = () => {
  if(chartMap.has("compareEchart"))
  {
    let chart=chartMap.get("compareEchart")!
    let option=optionMap.get("compareEchart")!
    chart.setOption(option)
    return 
  }
  let compareEchart = echarts.init(comparePieContainer.value)
  chartMap.set("compareEchart",compareEchart)
  let option: EChartsOption = {
    title: {
      left: 'center',
      subtext: '请求状态统计',
      subtextStyle: {
        color: ' #81D8CF'
      },
    },
    tooltip: {
      trigger: 'item'
    },

    series: [
      {
        name: '请求状态',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 0,
          borderColor: '#fff',
          borderWidth: 0
        },
        color: [
          'chartreuse',
          '#E60000',
        ],
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 20,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: result
      }
    ]
  };
  optionMap.set("compareEchart",option)
  option && compareEchart.setOption(option)
}

const initAccessTrends = () => {
  if(chartMap.has("accessTrendsEchart"))
  {
    let chart=chartMap.get("accessTrendsEchart")!
    let option=optionMap.get("accessTrendsEchart")!
    chart.setOption(option)
    return 
  }
  let accessTrendsEchart = echarts.init(visitTrendsContainer.value)
  chartMap.set("accessTrendsEchart",accessTrendsEchart)
  let option: EChartsOption = {
    tooltip: {
      trigger: 'axis',
      position: function (pt) {
        return [pt[0], '10%'];
      }
    },
    title: {
      left: 'center',
      text: '今日访问趋势',
      textStyle: {
        color: ' #81D8CF'
      }
    },
    xAxis: {
      type: 'time',
      boundaryGap: false
    },
    yAxis: {
      type: 'value',
      boundaryGap: [0, '100%']
    },
    dataZoom: [
      {
        type: 'inside',
        start: 0,
        end: 20
      },
      {
        start: 0,
        end: 20
      }
    ],
    series: [
      {
        name: 'Fake Data',
        type: 'line',
        smooth: true,
        symbol: 'none',
        areaStyle: {},
        data: accessTrendData
      }
    ]
  };
  optionMap.set("accessTrendsEchart",option)
  option && accessTrendsEchart.setOption(option);
}

const initMap = () => {
  if(chartMap.has("mapEchart"))
  {
    let chart=chartMap.get("mapEchart")!
    let option=optionMap.get("mapEchart")!
    chart.setOption(option)
    return 
  }
  echarts.registerMap('china', MapJson)
  let mapEchart = echarts.init(mapContainer.value)
  chartMap.set("mapEchart",mapEchart)
  let option: EChartsOption = {
    backgroundColor: '#071534',
    title: {
      left: 'center',
      textStyle: {
        color: '#fff'
      }
    },
    geo: {
      map: 'china',
      show: true,
      roam: false,
      itemStyle: {
        areaColor: '#86909c',
        borderColor: '#071534',
        borderWidth: 0.5
      },
      emphasis: {
        label: {
          color: '#1e80ff'
        },
        itemStyle: {
          areaColor: '#4e5969'
        },
      },
    },
    series: {
      name: '弱',
      type: 'scatter',
      progressive: 1e6,
      coordinateSystem: 'geo',
      symbolSize: 3,
      blendMode: 'lighter',
      large: true,

      itemStyle: {
        color: 'rgb(255, 2555, 0)'
      },
      silent: true,
      dimensions: ['lng', 'lat'],
      data: gnote,

    }
  }
  optionMap.set("mapEchart",option)
  option && mapEchart.setOption(option);
}

const initShowNumber = (active: number, online: number) => {
  toOrderNum('' + active, activeNumber, numberItem1)
  toOrderNum('' + online, onlineNumber, numberItem2)
}
const setNumberTransform = (orderNum: string[], numberItem: any) => {
  const numberItems = numberItem.value
  const numberArr = orderNum.filter((item) => !isNaN(+item))
  for (let index = 0; index < numberItems.length; index++) {
    const elem = numberItems[index]
    elem.style.transform = `translate(-50%, -${Number(numberArr[index]) * 10}%)`
  }
}

const toOrderNum = (num: string, orderNum: string[], numberItem: any) => {
  if (num.length < 8) {
    num = "0" + num
    toOrderNum(num, orderNum, numberItem)
  } else {
    num = num.slice(0, 2) + ',' + num.slice(2, 5) + ',' + num.slice(5, 8)
    for (let index = 0; index < orderNum.length; index++) {
      orderNum[index] = num[index]
    }
  }
  setNumberTransform(orderNum, numberItem)
}


</script>
<template>
  <div class="map_container" ref="mapContainer">
  </div>
  <div class="logo">
  </div>
  <br />
  <br />
  <br />
  <br />
  <br />
  <div class="table">
    <div class="left">
      <div class="left_first">
        <div class="left_first_info">
          <div class="info_pv">
            <span>{{ todayInfo.pv }}</span>
            <div class="dot">
              <i class="dotClass" style="background-color: chartreuse;"></i>
              <i>今日pv</i>
            </div>
          </div>
          <div class="info_uv">
            <span>{{ todayInfo.uv }}</span>
            <div class="dot">
              <i class="dotClass" style="background-color: aqua;"></i>
              <i>今日uv</i>
            </div>
          </div>
          <div class="info_error">
            <span>{{ todayInfo.error }}</span>
            <div class="dot">
              <i class="dotClass" style="background-color: yellow;"></i>
              <i>错误</i>
            </div>
          </div>
          <div class="info_exception">
            <span>{{ todayInfo.exception }}</span>
            <div class="dot">
              <i class="dotClass" style="background-color:deeppink;"></i>
              <i>异常</i>
            </div>
          </div>
          <div class="info_warn">
            <span>{{ todayInfo.warn }}</span>
            <div class="dot">
              <i class="dotClass" style="background-color:red ;"></i>
              <i>告警</i>
            </div>
          </div>
        </div>
      </div>
      <div class="left_second">
        <div class="left_second_title">
          <el-table class="top-table" style="width: 100%;text-align:center;" empty-text=" ">
            <el-table-column fixed prop="date" label="时间" width="auto" />
            <el-table-column prop="describe" label="异常信息" width="auto" />
            <el-table-column prop="interface" label="接口" width="auto" />
          </el-table>
          <vue3-seamless-scroll class="seamless" :list="exResultData" :hover="true" :step="0.2" :delay="10"
            :limitScrollNum="4" :singleWaitTime="1000" :wheel="true" :isWatch="true">
            <el-table class="bottom-table" :data="exResultData" style="width: 100%;text-align:center;" empty-text=" ">
              <el-table-column fixed prop="time" label="时间" width="auto" />
              <el-table-column prop="description" label="异常信息" width="auto" />
              <el-table-column prop="path" label="接口" width="auto" />
            </el-table>
          </vue3-seamless-scroll>
        </div>
      </div>
      <div class="left_third">
        <div class="left_third_title">
          <el-table class="top-table" style="width: 100%;text-align:center;" empty-text=" ">
            <el-table-column label="中奖信息">
              <el-table-column prop="id" label="用户" width="auto" />
              <el-table-column prop="activityName" label="活动" width="auto" />
              <el-table-column prop="awardName" label="奖项" width="auto" />
              <el-table-column prop="prizeName" label="奖品" width="auto" />
            </el-table-column>
          </el-table>
          <vue3-seamless-scroll class="seamless" :list="drawResultData" :hover="true" :step="0.2" :delay="10"
            :limitScrollNum="4" :singleWaitTime="1000" :wheel="true" :isWatch="true">
            <el-table class="bottom-table" :data="drawResultData" style="width: 100%;text-align:center;" empty-text=" ">
              <el-table-column label="中奖信息">
                <el-table-column prop="id" label="用户" width="auto" />
                <el-table-column prop="activityName" label="活动" width="auto" />
                <el-table-column prop="awardName" label="奖项" width="auto" />
                <el-table-column prop="prizeName" label="奖品" width="auto" />
              </el-table-column>
            </el-table>
          </vue3-seamless-scroll>
        </div>
      </div>
      <div class="left_forth">
        <div class="left_forth_first" ref="visitTrendsContainer">
        </div>
        <div class="info">
          <div class="comparePieContainer" ref="comparePieContainer">
          </div>
          <div class="newUserContainer" ref="newUserContainer">
          </div>
        </div>
      </div>
    </div>
    <div class="mid">
      <div class="person_show">
        <div class="chartNum_first">
          <h3 class="orderTitle">今日活跃人数</h3>
          <div class="box-item">
            <li :class="{ 'number-item': !isNaN(+item), 'mark-item': isNaN(+item) }" v-for="(item, index) in activeNumber"
              :key="index">
              <span v-if="!isNaN(+item)">
                <i ref="numberItem1">0123456789</i>
              </span>
              <span class="comma" v-else>{{ item }}</span>
            </li>
          </div>
        </div>
        <div class="chartNum_second">
          <h3 class="orderTitle">实时在线人数</h3>
          <div class="box-item">
            <li :class="{ 'number-item': !isNaN(+item), 'mark-item': isNaN(+item) }" v-for="(item, index) in onlineNumber"
              :key="index">
              <span v-if="!isNaN(+item)">
                <i ref="numberItem2">0123456789</i>
              </span>
              <span class="comma" v-else>{{ item }}</span>
            </li>
          </div>
        </div>
      </div>
    </div>
    <div class="right">
      <div class="right_first">
        <div class="day">
          <div>
            <span class="num_text">30天</span>
          </div>
          <div>
            <span class="num_text">7天</span>
          </div>
          <div>
            <span class="num_text">昨天</span>
          </div>
        </div>
        <div class="info_number">
          <div class="info_number_pv">
            <span>1</span>
            <div class="dot">
              <i class="dotClass" style="background-color: chartreuse;"></i>
              <i>pv</i>
            </div>
          </div>
          <div class="info_number_uv">
            <span>1</span>
            <div class="dot">
              <i class="dotClass" style="background-color: yellow;"></i>
              <i>uv</i>
            </div>
          </div>
        </div>
      </div>
      <div class="right_second">
        <div class="city">
          <span>最多访问国家</span>
          <span class="provinceRank" v-for="ran in areaRank.country">{{ ran }}</span>
        </div>
        <div class="province">
          <span>最多访问省份</span>
          <span class="provinceRank" v-for="ran in areaRank.province">{{ ran }}</span>
        </div>
        <div class="country">
          <span>最多访问城市</span>
          <span class="provinceRank" v-for="ran in areaRank.city">{{ ran }}</span>
        </div>
      </div>
      <div class="right_third">
        <div class="right_third_first" ref="browserContainer">
        </div>
        <div class="right_third_second" ref="mediaContainer">
        </div>
      </div>
      <div class="right_forth" ref="interfaceTopContainer">
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.el-dialog {

}
.dialog-footer button:first-child {
  margin-right: 10px;
}
.seamless {
  width: 100%;
  height: 190px;
  overflow: hidden;
}

:deep .top-table .el-table__body-wrapper {
  display: none;
}

:deep .bottom-table .el-table__header-wrapper {
  display: none;
  width: 100%;
}

.table {
  height: 100vh;
  width: 100vw;
  position: fixed;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.dot {
  display: flex;
}

.dotClass {
  width: 10px;
  border-radius: 10%;
  display: block
}

.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}

.el-scrollbar_view {
  height: 70%;
}

.left {
  height: 100%;
  width: 40%;
  display: flex;
  flex-direction: column;
}

.left_first {
  margin-left: 10px;
  height: 10%;
  width: 600px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
}

.left_first_info {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 20px;
  font-size: 20px;
  color: #03e9f4;
}


.left_second {
  margin-left: 10px;
  margin-top: 20px;
  height: 20%;
  width: 460px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  display: flex;
  flex-direction: column;
}

.left_second_title {
  height: 73%;
  width: 100%;
  margin: 20px;
  color: #81D8CF;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  font-weight: bold;
}



.left_third {
  margin-top: 20px;
  margin-left: 10px;
  height: 30%;
  width: 400px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
}

.left_third_title {
  height: 78%;
  width: 100%;
  margin: 20px;
  color: #81D8CF;
  display: flex;
  justify-content: space-between;
  // font-size: 15px;
  font-weight: bold;
  flex-direction: column;
}

.left_forth {
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  margin-left: 10px;
  height: 30%;
  width: 1100px;
}

.left_forth_first {
  height: 90%;
  width: 700px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
}

.info {
  // display: flex;
  // flex-direction: row;
  height: 100%;
  width: 1000px;
}

.comparePieContainer {
  float: left;
  margin-top: 30px;
  height: 80%;
  width: 40%;
}

.newUserContainer {
  margin-left: 200px;
  margin-top: 20px;
  height: 90%;
  width: 40%;
}

.mid {
  margin-top: 10px;
  height: 100%;
  width: 20vw;
}

.right {
  height: 100%;
  width: 40%;
  display: flex;
  flex-direction: column;
}

.right_first {
  height: 14%;
  width: 490px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  margin-left: auto;
  margin-right: 10px;
  display: flex;
  flex-direction: column;
  font-size: 20px;
  color: #03e9f4;
  font-weight: bold;
}

.day {
  width: auto;
  height: 10%;
  display: flex;
  margin: auto;
  font-size: 20px;
  margin-top: 10px;
}

.day div {
  text-align: center;
  box-sizing: border-box;
  width: 100px;
  height: 100%;
}

.info_number {
  width: 100%;
  height: 50%;
  display: flex;
  justify-content: space-around;
  font-size: 20px;
  margin: 20px;
}

.right_second {
  height: 25%;
  width: 450px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  margin-top: 20px;
  margin-right: 10px;
  margin-left: auto;
  display: flex;
  justify-content: space-around;
  font-size: 20px;
  color: #03e9f4;
  font-weight: bold;
  text-align: center;
}

.city {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  height: 25%;
}

.province {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  height: 25%;
}

.country {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  height: 30%;

}

.right_third {
  margin-top: 20px;
  margin-left: auto;
  margin-right: 10px;
  height: 20%;
  width: 600px;
  display: flex;
  flex-direction: row;
}

.right_third_first {
  height: 100%;
  width: 50%;
  margin-right: 2%;
}

.right_third_second {
  height: 100%;
  width: 50%;
  margin-left: 2%;
}

.right_forth {
  margin-top: 20px;
  margin-left: auto;
  margin-right: 10px;
  background-image: url(../assets/border.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 28%;
  width: 630px;
}



.logo {
  height: 12%;
  width: 100%;
  background-image: url(../assets/logo.png);
  background-size: 100% 100%;
  background-repeat: no-repeat;
  position: fixed;

}

.map_container {
  width: 100vw;
  height: 140vh;
  position: fixed;
  z-index: 0;
}

.person_show {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.box-item {
  position: relative;
  height: 70px;
  font-size: 70px;
  line-height: 40px;
  text-align: center;
  list-style: none;
  color: #2D7CFF;
  writing-mode: vertical-lr;
  text-orientation: upright;

  -moz-user-select: none;

  -webkit-user-select: none;

  -ms-user-select: none;

  -khtml-user-select: none;

  user-select: none;

}

.mark-item {
  width: 10px;
  height: 80px;
  margin-right: 5px;
  line-height: 10px;
  font-size: 40px;
  position: relative;

  &>span {
    position: absolute;
    width: 100%;
    bottom: 0;
    writing-mode: vertical-rl;
    text-orientation: upright;
  }
}

.number-item {
  width: 39px;
  height: 65px;
  background: #ccc;
  list-style: none;
  margin-right: 5px;
  background: rgba(250, 250, 250, 1);
  border-radius: 4px;
  border: 1px solid rgba(221, 221, 221, 1);
  background-image: url("../assets/numbg.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;

  &>span {
    position: relative;
    display: inline-block;
    margin-right: 10px;
    width: 100%;
    height: 100%;
    writing-mode: vertical-rl;
    text-orientation: upright;
    overflow: hidden;

    &>i {
      font-style: normal;
      position: absolute;
      top: 0px;
      left: 50%;
      transform: translate(-50%, 0);
      transition: transform 1s ease-in-out;
      letter-spacing: 10px;
    }
  }
}

.orderTitle {
  font-size: 15px;
  color: gold;
  text-align: center;
}

.number-item:last-child {
  margin-right: 0;
}

.chartNum_first {
  margin-bottom: 10px;
}
</style>
