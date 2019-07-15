$(function () {
    var charts = [];
    //房产
    var houseChart = echarts.init(document.getElementById("chart-house"));
    charts.push(houseChart);
    var houseData = [
        {
            value:14525422,
            name:'有产权'
        },
        {
            value:45677656,
            name:'无产权'
        }];
    var houseoption = {
        color: ['#FF6600','#e7505a'],
        title : {
            text: '产权比例分布图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data:['有产权','无产权'] ,
        },
        series:[{
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            label:{
                formatter:"{b} {d}%",
            },
            data: houseData
        }
        ]
    };
    houseChart.setOption(houseoption);
    //租赁
    var leaseChart = echarts.init(document.getElementById("chart-lease"));
    charts.push(leaseChart);
    var leaseData = [
        {
            value:145254,
            name:'出租面积'
        },
        {
            value:5556,
            name:'空置面积'
        }];
    var leaseoption = {
        color: ['#FF6600','#ffc334'],
        title : {
            text: '租赁面积比例图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data:['出租面积','空置面积'] ,
        },
        series:[{
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
           label:{
               formatter:"{b} {d}%",
           },
            data: leaseData
        }
        ]
    };
    leaseChart.setOption(leaseoption);

    //法务
    var lawChart = echarts.init(document.getElementById("chart-law"));
    charts.push(lawChart);
    var lawoption ={
        title : {
            text: '法务案件分布图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter: function (params) {
                var tar = params[1];
                return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type : 'category',
            splitLine: {show:false},
            data : ['总计','民事案件','刑事案件','非诉事件','行政诉讼','合同']
        },
        yAxis: {
            type : 'value'
        },
        series: [
            {
                name: '辅助',
                type: 'bar',
                stack:  '总量',
                itemStyle: {
                    normal: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'rgba(0,0,0,0)'
                    },
                    emphasis: {
                        barBorderColor: 'rgba(0,0,0,0)',
                        color: 'rgba(0,0,0,0)'
                    }
                },
                data: [0, 10, 9, 8, 6, 0]
            },
            {
                name: '案件',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data:[23,13, 1, 1, 2,6]
            }
        ]
    } ;
    lawChart.setOption(lawoption)
    //审计
    var auditChart = echarts.init(document.getElementById("chart-audit"));
    charts.push(auditChart);
    var auditoption = { color: ['#3398DB'],
        title : {
            text: '每月审计统计图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['2018-11','2018-12','2019-1', '2019-2', '2019-4'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'审计次数',
                type:'bar',
                barWidth: '60%',
                data:[10, 23, 14, 21,16]
            }
        ]};
    auditChart.setOption(auditoption);
    //预警
    var warnChart = echarts.init(document.getElementById("chart-warn"));
    charts.push(warnChart);
    var warnData = [
        {
            value:34,
            name:'过期'
        },
        {
            value:67,
            name:'到期'
        },
        {
            value:78,
            name:'未到期'
        }];
    var warnoption = {
        title : {
            text: '企业到期/过期分布图',
            x:'center',
            y:10
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            right: 10,
            top: 20,
            bottom: 20,
            data:['过期','到期','未到期'] ,
        },
        series:[{
            type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            label:{
                formatter:"{b} {d}%",
            },
            data: warnData
        }
        ]
    };
    warnChart.setOption(warnoption);
    // 适应bootstrap变换尺寸
    window.onresize = function () {
        for(var i = 0; i < charts.length; i++){
            charts[i].resize();
        }
    }
});