<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Onboard Drivers - LMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        .form-container { max-width: 500px; margin: 30px auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 8px; font-weight: 600; color: #495057; }
        input { width: 100%; padding: 10px; border: 1px solid #ced4da; border-radius: 4px; box-sizing: border-box; }
        .btn-submit { background: #0d6efd; color: white; padding: 12px; border: none; border-radius: 4px; font-weight: bold; width: 100%; cursor: pointer; }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="main-content">
    <div class="header">
        <h1>Driver Roster Administration</h1>
        <div class="user-info">Welcome Admin</div>
    </div>

    <div class="form-container">
        <h2 style="margin-top:0;">Register New Fleet Driver</h2>
        <form id="driverForm">
            <div class="form-group"><label>Driver Legal Name</label><input type="text" id="dname" required></div>
            <div class="form-group"><label>Mobile Contact Number</label><input type="number" id="dcontact" required></div>
            <button type="submit" class="btn-submit">Add Driver to Pool</button>
        </form>
    </div>
</div>

<script>
    document.getElementById('driverForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const payload = {
            name: document.getElementById('dname').value,
            contact: parseInt(document.getElementById('dcontact').value)
        };

        fetch('${pageContext.request.contextPath}/savedriver', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        })
        .then(res => res.json())
        .then(data => {
            if (data.status === 406) {
                alert("Conflict: " + data.message);
            } else {
                alert("Success: " + data.message);
                document.getElementById('driverForm').reset();
            }
        });
    });
</script>
</body>
</html>