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
                            <td><a class="waves-effect waves-light btn" onclick="blockUser('${user.name}')">Заблокировать пользователя</a></td>
                        </tr>

                    </#list>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    function blockUser(username) {
        $.ajax({
            url:'/ui/block',
            method:'POST',
            dataType:'json',
            data: {'username':username},
            success: function(){
                alert("user " + username + " is blocked");
            }
        });
    }
</script>
</body>
</html>