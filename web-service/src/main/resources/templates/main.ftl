<!DOCTYPE html>
<html>
<head>
    <title>Registr</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="/js/app.js"></script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="row">
        <div class="input-field col s6">
            <form action="/ui/register" method="post">
                <div class="form-group">
                    <label for="connect">Enter name:</label>
                    <input id="name" type="text" name="name" class="validate" placeholder="Your name here..."/>
                    <input id="password" type="password" name="password" class="validate" placeholder="Your password here..."/>
                    <input id="connect" class="waves-effect waves-light btn" type="button" value="Get dog image">
                </div>
                <button type="submit"  class="waves-effect waves-light btn">Register</button>
                <div class="form-group">
                    <label for="image"></label>
                    <img src="" id="image"/>
                    <input hidden id="image-input" name="imgUrl"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>