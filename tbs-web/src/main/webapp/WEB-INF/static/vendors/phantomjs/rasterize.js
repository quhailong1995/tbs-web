/**
 * 其中最重要的方法便是 viewportSize 和 clipRect 属性。

viewportSize 是视区的大小，你可以理解为你打开了一个浏览器，然后把浏览器窗口拖到了多大。

clipRect 是裁切矩形的大小，需要四个参数，前两个是基准点，后两个参数是宽高。

 */

var page = require('webpage').create(),
    system = require('system'),t,
    rootaddress ,
    exportaddress = '/admin/tbs/query/report/exportpdfexample?',
    addressParam,  size, pageWidth, pageHeight,aa,
    loginPage = '/admin/login',
    loginData = 'username=admin&password=123456';


	page.settings.loadImages = false;  //为了提升加载速度，不加载图片  
	page.settings.resourceTimeout = 10000;//超过10秒放弃加载  
	//修改页面默认的userAgent
	page.settings.userAgent = "SpecialAgent";
	

if (system.args.length === 1) {  
		console.log('Usage: loadspeed.js <some URL>');  
		//这行代码很重要。凡是结束必须调用。否则phantomjs不会停止  
		 phantom.exit();  
	}  	
	

if (system.args.length < 2 || system.args.length > 5) {
    console.log('Usage: rasterize.js URL filename [paperwidth*paperheight|paperformat] [zoom]');
    console.log('  paper (pdf output) examples: "5in*7.5in", "10cm*20cm", "A4", "Letter"');
    console.log('  image (png/jpg output) examples: "1920px" entire page, window width 1920px');
    console.log('                                   "800px*600px" window, clipped to 800x600');
    phantom.exit(1);
} else {
	rootaddress = system.args[1];
	addressParam = system.args[2];
    exportaddress = rootaddress + exportaddress + addressParam;
    //output = system.args[2];
    page.viewportSize = { width: 1000, height: 1500 };
    if (system.args.length > 3 && system.args[2].substr(-4) === ".pdf") {
        size = system.args[3].split('*');
        page.paperSize = size.length === 2 ? { width: size[0], height: size[1], margin: '100px' }
                                           : { format: system.args[3], orientation: 'portrait', margin: '1cm' };
    } else if (system.args.length > 3 && system.args[3].substr(-2) === "px") {
        size = system.args[3].split('*');
        if (size.length === 2) {
            var pageWidth = parseInt(size[0], 10),
                pageHeight = parseInt(size[1], 10);
            page.viewportSize = { width: pageWidth, height: pageHeight };
            page.clipRect = { top: 0, left: 0, width: pageWidth, height: pageHeight };
        } else {
            console.log("size:", system.args[3]);
            var pageWidth = parseInt(system.args[3], 10),
                pageHeight = parseInt(pageWidth * 3/4, 10); // it's as good an assumption as any
            console.log ("pageHeight:",pageHeight);
            page.viewportSize = { width: pageWidth, height: pageHeight };
        }
    }
    if (system.args.length > 4) {
        page.zoomFactor = system.args[4];
    }
    
    t = Date.now();//看看加载需要多久。
   // page.open(address, function (status) {
    page.open(rootaddress + loginPage, 'post', loginData, function (status) {
        if (status !== 'success') {
            console.log('Unable to load the address!');
            phantom.exit(1);
        } else {
        	t = Date.now() - t;  
        	console.log('Loading time ' + t + ' msec');  
        	console.log("_____"+page.url);

        	page.open(exportaddress, function (status) {
       		 if (status !== 'success') {
       	            console.log('Unable to load the address!');
       	            phantom.exit(1);
       	        } else {

       	            window.setTimeout(function () {
       	                
       	            	var aaaa = page.renderBase64("JPEG");
       	            	console.log("imageDataBase64data:image/jpeg;base64,"+aaaa);
       	                phantom.exit();
       	            }, 800);
       	        }
       	});
        	
        }
    });
}