<html>
<head>
    <title>Ejemplo sencillo de web service con RESTEasy</title>
    <script type="text/javascript" src="http://localhost:8080/helloworld-resteasy/rest-jsapi"></script>
</head>
<body>
    <script type="text/javascript">
        alert(HelloWorldResourceImpl.getSaluda());
        alert(HelloWorldResourceImpl.getSaludaA({nombre:'picodotdev'}));
    </script>
</body>
</html>