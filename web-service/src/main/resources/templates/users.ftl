<!DOCTYPE html>
<html>
<head>
    <title>All users</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>    <#--<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>-->
</head>
<body>

<h2>Hello, ${Request.username!}</h2>
<a href="/ui/logout">Logout</a>

<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <table class="highlight">
                <thead >
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Photo</th>
                </tr>
                </thead>
                <tbody>
                <#if users??>
                    <#list users as user>
                        <tr>
                        <#--<td>${user.id}</td>-->
                            <td scope="row">${user.name}</td>
                            <td><img src="${user.imgUrl}" </td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>