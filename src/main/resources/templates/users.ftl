<!DOCTYPE html>
<html lang="en">
<body>
<table>
    <tr>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Email</th>
    </tr>
    <#list model["users"] as user>
    <tr>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
    </tr>
</
#list>
</table>
</body>
</html>