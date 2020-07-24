<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
    <head>
        <title>PawDopt</title>
    </head>

    <body>
        <h2>Welcome to PawDopt!</h2>

        <p>
            User: <security:authentication property="principal.username" />
            <br><br>
            Role (s): <security:authentication property="principal.authorities" />
        </p>

        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
        </form:form>

        <!-- Show only to Manager role -->
        <security:authorize access="hasRole('MANAGER')">
            <!-- Add link to point to /leaders -->
            <p>
                <a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
                Only for Managers!
            </p>
        </security:authorize>

        <!-- Show only to Admin role -->
        <security:authorize access="hasRole('ADMIN')">
            <!-- Add link to point to /systems -->
            <p>
                <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
                Only for Sys Admins!
            </p>
        </security:authorize>

    </body>
</html>