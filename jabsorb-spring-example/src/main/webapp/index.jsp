<html>
<script type="text/javascript" src="js/jquery-1.4.2.js"> </script>
<script type="text/javascript" src="js/jsonrpc.js"> </script>

<script type="text/javascript">
var jsonrpc = new JSONRpcClient("/jabsorb-spring-example/JSON-RPC/");
</script>
<body>
<h2>Exported services</h2>
<div class="servicesContainer">
</div>
<script type="text/javascript">
var services = jsonrpc.system.listMethods();
for (v in services) {
	$('.servicesContainer').append('<p>' + services[v]+ '</p>');
}
</script>

</body>
</html>
