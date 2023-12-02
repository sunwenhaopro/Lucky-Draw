<template>
    <div class="text">6666</div>
    <div ref="map" class="map">
    </div>
</template>
<script setup lang="ts">
import * as echarts from "echarts"
import 'echarts-gl'
import { onMounted, ref } from "vue";
import jsonMap from '@/assets/china.json'
type EChartsOption = echarts.EChartsOption;
const map = ref()
const initMap = () => {
    echarts.registerMap('china', jsonMap)
    let chart = echarts.init(map.value)
    let option: EChartsOption = {
        backgroundColor: '#071534',
        title: {
            text: 'ChinaMap',
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
            data: [[113.82033, 23.10469], [121.47361, 30.27469], [116.32392, 22.9878], [114.36778, 23.12686], [119.48147, 30.99669],
                [114.99485, 36.89023], [120.6623, 30.27523], [117.87779, 30.9595], [114.34845, 28.527], [115.62595, 30.45446],
                [120.40218, 34.96075], [120.85229, 27.39702], [117.63664, 28.0239], [118.59459, 30.04038], [114.82303, 35.7654],
                [114.46982, 27.34782], [116.54057, 38.55955], [118.75974, 29.4238], [115.8386, 30.6279], [116.68079, 25.4977],
                [118.78804, 34.5969], [115.66807, 30.7444], [120.66669, 32.5087], [118.37452, 27.9969], [117.03805, 30.5854],
                [116.48503, 26.4597], [114.52076, 28.6649], [120.88869, 34.0003], [116.4447, 30.565], [114.58579, 29.479],
                [120.75856, 35.9997], [116.4478, 28.0744], [118.9766, 30.697], [116.3887, 30.723], [115.8576, 30.55],
                [120.6842, 30.4088], [120.09733, 32.579], [117.65427, 30.774], [114.4403, 35.988], [120.6465, 32],
                [120.6465, 32], [120., 30.2331],[119.2816,26.0782],[113.2644,23.1291],[106.5046,29.5332],[121.4737,31.2304],
                [104.0665,30.5728],[103.8343,36.0611],[114.5149,38.0428],[117.2830,31.8612],[112.9823,28.1941],[108.9480,34.2632],
                [126.6425,45.7560],[102.7123,25.0406],[120.1551,30.2741],[113.5491,22.1987],[116.4074,39.9042],[123.4315,41.8057],
                [87.6177,43.7928],[106.2309,38.4872],[111.6080,40.8086],[118.7969,32.0603],[125.3245,43.8868],[114.3054,30.5928],
                [113.9213,35.3013],[120.6196,31.2994],[121.4890,25.1055],[109.7813,39.6083],[112.2384,30.7378],[114.1988,22.3794],
                [121.5440,25222]]
        }
    }
    option && chart.setOption(option);
}
onMounted(() => {
    initMap()
})
</script>
<style>

@import url('https://fonts.googleapis.com/css2?family=Orbitron&display=swap');

.text {
    font-family: 'Orbitron', sans-serif;
    /* color:RGB(0,47,167) ;
     */
     color: #03e9f4;

    text-shadow: 0 0 1px  ;
}


.map {
    width: 100vw;

    height: 140vh;
    position: fixed;
}
</style>