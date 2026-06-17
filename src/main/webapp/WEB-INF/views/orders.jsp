<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Orders - LMS</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
    <style>
        .work-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 30px; margin-top: 20px; }
        .form-panel { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
        .form-panel h2 { margin-top: 0; margin-bottom: 20px; font-size: 18px; padding-bottom: 10px; border-bottom: 2px solid #0d6efd; color: #0d6efd; }
        .form-panel.workflow h2 { border-bottom-color: #198754; color: #198754; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 6px; font-size: 13px; font-weight: 600; color: #495057; }
        input, textarea { width: 100%; padding: 10px; border: 1px solid #ced4da; border-radius: 4px; box-sizing: border-box; }
        .btn { padding: 12px; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; width: 100%; color: white; }
        .btn-blue { background: #0d6efd; }
        .btn-green { background: #198754; }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="main-content">
    <div class="header">
        <h1>Order Operations Hub</h1>
        <div class="user-info">Welcome Admin</div>
    </div>

    <div class="work-grid">
        <div class="form-panel">
            <h2>📦 Book New Freight Order</h2>
            <form id="orderForm">
                <div class="form-group"><label>Order Date</label><input type="date" id="orderdate" required></div>
                <div class="form-group"><label>Cargo ID</label><input type="number" id="cargoid" required></div>
                <div class="form-group"><label>Cargo Name</label><input type="text" id="cargoName" required></div>
                <div class="form-group"><label>Description</label><textarea id="cargoDescription" rows="2"></textarea></div>
                <div class="form-group"><label>Weight (kg)</label><input type="number" id="cargowt" required></div>
                <div class="form-group"><label>Item Count</label><input type="number" id="cargocount" required></div>
                <div class="form-group"><label>Loading Address ID</label><input type="number" id="loadingid" required></div>
                <div class="form-group"><label>Unloading Address ID</label><input type="number" id="unloadingid" required></div>
                <button type="submit" class="btn btn-blue">Place New Order</button>
            </form>
        </div>

        <div class="form-panel workflow">
            <h2>⚙️ Advance Order Lifecycle Status</h2>
            <form id="lifecycleForm">
                <div class="form-group"><label>Target Order ID</label><input type="number" id="update_id" required></div>
                <div class="form-group"><label>Assign Carrier ID</label><input type="number" id="update_carrierid" required></div>
                <div class="form-group"><label>Transit Duration (Days)</label><input type="number" id="update_unloadingdays" value="2" required></div>
                <button type="submit" class="btn btn-green">Process Next Phase Step</button>
            </form>
        </div>
    </div>
</div>

<script>
    // Asynchronous POST to OrderController.java
    document.getElementById('orderForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const payload = {
            orderdate: document.getElementById('orderdate').value,
            cargoid: parseInt(document.getElementById('cargoid').value),
            cargoName: document.getElementById('cargoName').value,
            cargoDescription: document.getElementById('cargoDescription').value,
            cargowt: parseInt(document.getElementById('cargowt').value),
            cargocount: parseInt(document.getElementById('cargocount').value),
            loadingid: parseInt(document.getElementById('loadingid').value),
            unloadingid: parseInt(document.getElementById('unloadingid').value)
        };

        fetch('${pageContext.request.contextPath}/order', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        })
        .then(res => res.json())
        .then(data => {
            if (data.status === 406 || data.status === 404) {
                alert("Halt: " + data.message);
            } else {
                alert("Created: " + data.message);
                document.getElementById('orderForm').reset();
            }
        });
    });

    // Asynchronous PUT to OrderController.java
    document.getElementById('lifecycleForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const id = document.getElementById('update_id').value;
        const cid = document.getElementById('update_carrierid').value;
        const days = document.getElementById('update_unloadingdays').value;

        fetch('${pageContext.request.contextPath}/updateorder?id=' + id + '&carrierid=' + cid + '&unloadingdays=' + days, {
            method: 'PUT'
        })
        .then(res => res.json())
        .then(data => {
            if (data.status === 406 || data.status === 404) {
                alert("Rejected: " + data.message);
            } else {
                alert("Processed: " + data.message);
                document.getElementById('lifecycleForm').reset();
            }
        });
    });
</script>
</body>
</html>