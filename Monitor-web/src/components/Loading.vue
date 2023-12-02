<template>
    <div class="loading" ref="container"></div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, ref } from 'vue';
type EchartOption = echarts.EChartOption;
const container = ref()
const ininLoading = () => {
    let dom = container.value
    let loading = echarts.init(dom)
    let option: EchartOption
    option = {
        graphic: {
            elements: [
                {
                    type: 'text',
                    left: 'center',
                    top: 'center',
                    style: {
                        text: 'Lucky Draw',
                        fontSize: 150,
                        fontWeight: 'bold',
                        lineDash: [0, 200],
                        lineDashOffset: 0,
                        fill: 'transparent',
                        stroke: 'blue',
                        lineWidth: 1
                    },
                    keyframeAnimation: {
                        duration: 3000,
                        loop: true,
                        keyframes: [
                            {
                                percent: 0.7,
                                style: {
                                    fill: 'transparent',
                                    lineDashOffset: 200,
                                    lineDash: [200, 0]
                                }
                            },
                            {
                                // Stop for a while.
                                percent: 0.8,
                                style: {
                                    fill: 'transparent'
                                }
                            },
                            {
                                percent: 1,
                                style: {
                                    fill: 'black'
                                }
                            }
                        ]
                    }
                }
            ]
        }
    }
   // 等价于if(option) loading.setOption(option)
    option && loading.setOption(option)
}
onMounted(() => {
 ininLoading()
})
</script>
<style scoped>
.loading {
    height: 100vh;
    width: 100vw;
}
</style>