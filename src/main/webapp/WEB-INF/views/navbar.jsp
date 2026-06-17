<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar">
    <div class="logo">
        <h2>LMS</h2>
        <p>Logistics Management System</p>
    </div>
    <ul>
        <%-- Using dynamic context paths ensures Tomcat routes these seamlessly --%>
        <li><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/orders">Orders</a></li>
        <li><a href="${pageContext.request.contextPath}/drivers">Drivers</a></li>
        <li><a href="${pageContext.request.contextPath}/trucks">Trucks</a></li>
        <li><a href="#">Cargo</a></li>
        <li><a href="#">Loading</a></li>
        <li><a href="#">Unloading</a></li>
        <li><a href="#">Reports</a></li>
        <li><a href="${pageContext.request.contextPath}/">Logout</a></li>
    </ul>
</div>