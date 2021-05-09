; (function ($) {
    $.extend({
        "login": function () {
            var $win = $(window), $loginDiv = $('#_login_div_quick_'), $login = $('#login_button'), $errTip = $('#error_tips'), showLogin = function () {
                $loginDiv.css({
                    height: 330 + 'px',
                    width: 280 + 'px',
                    display: 'block'
                });
            }, $userTip = $('#uin_tips'), $passTip = $('#pwd_tips'), $operateTip = $('#operate_tips'), $delUserInput = $('#uin_del'), $u = $('#u'), $p = $('#p'),$v = $('#validateCode'), $switch = $('#qrswitch_logo'), $qr = $('#web_qr_login_show');
            $(function () { showLogin(); });
            $win.resize(function () { if ($loginDiv.is(':visible')) { showLogin(); } });
       
            $u.add($p).bind({
                'focus': function () {
                	$('#error_tips').hide();
                    var $this = $(this), $currTip = $this.attr('id') === 'u' ? ($operateTip.show(), $userTip) : $passTip;
                    $this.parent().css('background-position-y', '-45px');
                    $currTip.css('color', '#ddd');
                },
                'blur': function () {
                    var $this = $(this), $currTip = $this.attr('id') === 'u' ? ($operateTip.hide(), $userTip) : $passTip;
                    $this.parent().css('background-position-y', '-1px');
                    $currTip.css('color', '#aaa');
                    //if ($currTip === $passTip && $capsLock.is(':visible')) { $capsLock.hide(); }
                },
                'input': function (e) {
                    var $this = $(this), $currTip = $this.attr('id') === 'u' ? $userTip : $passTip;
                    if ($this.val()) {
                        if ($currTip.is(':visible')) {
                            $currTip.hide();
                            if ($currTip === $userTip) { $delUserInput.show(); }
                        }
                    } else {
                        $currTip.show();
                        if ($currTip === $userTip) { $delUserInput.hide(); }
                    }
                }
            });
            $p.keypress(function (e) {
                //if (((e.keyCode >= 65 && e.keyCode <= 90) && !e.shiftKey) || ((e.keyCode >= 97 && e.keyCode <= 122) && e.shiftKey)) { $capsLock.show(); } else { $capsLock.hide(); }
            });
            $delUserInput.click(function () { $u.val('').focus(); $userTip.show(); $delUserInput.hide(); });

            $login.click(function () {
                if (!$u.val()) {
                    $errTip.find('#err_m').text('请输入用户名！').end().show();
                    setTimeout(function () { $errTip.hide(); }, 5000);
                    return false;
                } else if (!$p.val()) {
                    $errTip.find('#err_m').text('您还没有输入密码！').end().show();
                    setTimeout(function () { $errTip.hide(); }, 5000);
                    return false;
                }else {
                    $('#loading_tips').show();
					$("#loginForm").submit();
                }
            });
			$(document).keyup(function(event){
			  if(event.keyCode ==13){
				$login.trigger("click");
			  }
			});
        }
    });
	
	//回车切换条件
	 $('input:text:first').focus(); 
		 document.onkeydown = function enterHandler(event)
		 {
		   var inputs = $("input");           //可自行添加其它过滤条件   
		   var browser = navigator.appName ;      //浏览器名称
		   var userAgent = navigator.userAgent;     //取得浏览器的userAgent字符串 
		   
		   var Code = '' ;
		   if(browser.indexOf('Internet')>-1)      // IE  
			Code = window.event.keyCode ;
		   else if(userAgent.indexOf("Firefox")>-1)   // 火狐
			Code = event.which;
		   else                     // 其它
			 Code = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
		  
		   if (Code == 13)               //可以自行加其它过滤条件
		   {
			 for(var i=0;i<inputs.length;i++)
			 {
			  if(inputs[i].id == document.activeElement.id)
			  {  
				i = i== (inputs.length - 1) ? -1 : i ;
				$('#'+ inputs[i+1].id ).focus()
				break;
			  }
			 }
		   }
		 }
}(jQuery));


