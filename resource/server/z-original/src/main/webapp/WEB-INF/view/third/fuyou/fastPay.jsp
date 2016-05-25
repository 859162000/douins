<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
  </head>
  <body>
    <form action = 'http://116.239.4.195:9056/jzh/500001.action' id="fastpayform" method="post">
   	<input type="hidden" name ="mchnt_cd" value="${sessionScope.fastPayParams.mchnt_cd}" />
   	<input type="hidden" name ="mchnt_txn_ssn" value="${sessionScope.fastPayParams.mchnt_txn_ssn}" />
   	<input type="hidden" name ="login_id" value="${sessionScope.fastPayParams.login_id}" />
   	<input type="hidden" name ="amt" value="${sessionScope.fastPayParams.amt}" />
   	<input type="hidden" name ="page_notify_url" value="${sessionScope.fastPayParams.page_notify_url}" />
   	<input type="hidden" name ="back_notify_url" value="${sessionScope.fastPayParams.back_notify_url}" />
   	<input type="hidden" name ="signature" value="${sessionScope.fastPayParams.signature}" />
   	  <input type="submit" value ="submit">
    </form>
  </body>
<!--   <script type="text/javascript">
  var fastpayform=document.getElementById('fastpayform');
  fastpayform.submit();
  </script> -->
</html>
