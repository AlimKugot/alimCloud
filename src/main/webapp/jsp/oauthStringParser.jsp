<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>access data</title>
</head>
<body>
    <script>
        function locationHashChanged() {
            if (location.hash) {
                const hash = location.hash.replace(/#/, '?');
                history.replaceState({}, '', hash);
                location.reload();
            }
        }
        setTimeout(locationHashChanged, 1)
    </script>
</body>
</html>
