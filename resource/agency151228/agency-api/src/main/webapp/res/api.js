/**
 * api.js
 * 对于 post 提交的请求数据，使用该文件的方法显示
 */

function shuchu(str){
	alert(str);
}

/*
 * 模拟 post 提交
 * */
var timestamp=new Date().getTime();
var baseUrl = "";
var constUrl = "res/api.html";
var token = "5bd311ba6a114ebc9c905f30f3b77ed1";

/**
 * 公共访问参数
 */
var requestParams = {
		accessToken : token,
		requestTrans : {
			transId: timestamp,
			transChannel: "01",
			transTime: timestamp,
			transId : timestamp,
			transType: "000"
		}
}
 
/**
 * 访问求情参数体
 * @param url
 * @param name
 * @param params
 */
function request(url, name, params){
	/* 设置 url */
	var pos = location.href.indexOf(constUrl);
	var url2 = location.href.substring(0, pos) + url;
		
	/* 添加属性 */
	requestParams[name] = params;
	requestParams["url"] = url2;
	
	/* JSON 格式显示 */
	var obj = JSON.stringify(requestParams); 
	document.write(obj);
	
	/* 添加超链接 */
	addHref(url2, obj);
}

/* 添加超链接 */
function addHref(url3, params2){
	document.body.appendChild(document.createElement("br"));
	document.body.appendChild(document.createElement("br"));
	
	var a=document.createElement("a");
	a.id="a";
	a.href=url3;
	a.innerText="";
	a.title="url";
	a.setAttribute("onclick", postRequest(url3, params2));
	document.body.appendChild(a);
}

/**
 * post 表单提交
 * @param URL
 * @param name2
 * @param PARAMS
 * @returns {___anonymous743_746}
 */
function post(URL,  PARAMS) {   
    var temp = document.createElement("form");        
    temp.action = URL;
    temp.method = "post"; 
    temp.style.display = "none"; 
    var opt = document.createElement("textarea"); 
    opt.name = "data";
    opt.value = PARAMS;
    temp.appendChild(opt); 
    
    document.body.appendChild(temp);    
    temp.submit();   
     
    alert("请求已提交");
//   var obj = JSON.stringify(temp); 
//   document.write(obj);
    
    //document.body.removeChild(temp);
    return temp;   
} 

/* jQuery 的 post 提交 */
function postRequest(url4,  params4){
	$.post(
			url4,			// 目标 url
			{
				data : params4			// 请求参数，json格式
			},
			
			function(data){		// 回调函数
				document.body.appendChild(document.createElement("br"));
				document.body.appendChild(document.createElement("br"));
				document.write(JSON.stringify(data));
			},
			
			"json"				// 返回值类型
			);
}

