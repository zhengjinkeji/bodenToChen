/**
 * 图1
 */
$(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'),'macarons');

    // 指定图表的配置项和数据
    option = {
        title : {
            text: '系列销售-饼状图',
            //subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['分集水器系列','管道系列','球阀系列','温控阀系列','自控系列','其他']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '50%'],
                data:[
                    {value:33555, name:'分集水器系列'},
                    {value:31240, name:'管道系列'},
                    {value:22434, name:'球阀系列'},
                    {value:13115, name:'温控阀系列'},
                    {value:11518, name:'自控系列'},
                    {value:5831, name:'其他'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(144, 238 ,144, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
})
/**
 * 图2
*/
$(function(){
	var dom = document.getElementById("main2");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	app.title = '环形图';

	option = {
		title:{
			text:"系列商品分布-环形图",
			x:"center"
		},
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data: ['分集水器系列','管道系列','球阀系列','温控阀系列','自控系列','其他']
	    },
	    series: [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['50%', '70%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:1350, name:'分集水器系列'},
	                {value:350, name:'管道系列'},
	                {value:1350, name:'球阀系列'},
	                {value:3350, name:'温控阀系列'},
	                {value:2220, name:'自控系列'},
	                {value:1111, name:'其他'},
	            ]
	        }
	    ]
	};
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
})

/**
 * 图3
 */
$(function(){
	var dom = document.getElementById("main3");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
		title:{
			text: '年销售额-折线图',
			x:"center"
		},
	    xAxis: {
	        type: 'category',
	        data: ['2010','2011','2012','2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020']
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [{
	        data: [ 131888,143928, 170444,178111, 194777, 200000, 203090,243940,253940,260030 ,280030],
	        type: 'line'
	    }]
	};
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
})

/**
 * 图4
 */

$(function () {
    var myChart4 = echarts.init(document.getElementById('main4'),'macarons');

    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    option = {
        title: {
            text: '商品类别分布-柱状图',
            x:'center'
            // subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
        },
        color: [ '#5ab1ef','#b6a2de','#ffb980'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },

        legend: {
            orient: 'vertical',
            right: 'right',
            data: ['品牌专业', '品牌定制', '喜豆专区']
        },
        toolbox: {
            show: false,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: ['2011','2012', '2013', '2014', '2015', '2016','2017','2018','2019','2020']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '品牌专业',
                type: 'bar',
                barGap: 0,
                // label: labelOption,
                data: [6320, 7332, 8301, 9334, 10290,11194,12265,11204,13164,14164]
            },
            {
                name: '品牌定制',
                type: 'bar',
                // label: labelOption,
                data: [5220, 6182, 7191, 8234, 8290,9204,10065,12204,9294,12194]
            },
            {
                name: '喜豆专区',
                type: 'bar',
                // label: labelOption,
                data: [4150, 5232, 6201, 7154, 8190,9354,10265,11124,9264,11294]
            }
        ]
    };

    myChart4.setOption(option);
})
/**
 * 图5
 */
$(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main5'),'macarons');

    // 指定图表的配置项和数据
    option = {
        title : {
            text: '商品价格分布-饼状图',
            //subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['0-100','101-200','201-300','301-400','401-500','501-600','601-700','701-800','801-900','901-1000','1000~']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '50%'],
                data:[
                    {value:35, name:'0-100'},
                    {value:110, name:'101-200'},
                    {value:234, name:'201-300'},
                    {value:235, name:'301-400'},
                    {value:158, name:'401-500'},
                    {value:581, name:'501-600'},
                    {value:320, name:'601-700'},
                    {value:320, name:'701-800'},
                    {value:345, name:'801-900'},
                    {value:345, name:'901-1000'},
                    {value:158, name:'1000~'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(144, 238 ,144, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
})

/**
 * 图6
 * @returns
 */

$(function () {
	var dom = document.getElementById("main6");
	var myChart = echarts.init(dom);
	var app = {};
	option = null;
	option = {
		title:{
			text: '代理商年销售额-柱状图',
            //subtext: '纯属虚构',
            x:'center'
		},
	    xAxis: {
	        type: 'category',
	        data: [ '刘一', '陈二','张三', '李四', '王五', '赵六', '冯七','周八', '吴九', '郑十']
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [{
	        data: [114900, 102222, 98222, 88880, 71111,64900, 52222, 40135, 35888, 31111],
	        type: 'bar'
	    }]
	};
	;
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}

})
/**
 * 图7
 */
$(function () {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main7'),'macarons');

    // 指定图表的配置项和数据
    option = {
        title : {
        	text: '区域销售额-饼状图',
            //subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['东北地区','华北地区','华中地区','华南地区','华东地区','西北地区','西南地区']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '50%'],
                data:[
                    {value:10335, name:'东北地区'},
                    {value:31110, name:'华北地区'},
                    {value:32189, name:'华中地区'},
                    {value:23135, name:'华南地区'},
                    {value:11135, name:'华东地区'},
                    {value:8310, name:'西北地区'},
                    {value:7320, name:'西南地区'},
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(144, 238 ,144, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
})
