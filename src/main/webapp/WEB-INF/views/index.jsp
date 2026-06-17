<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html data-theme="light">
<head>
    <meta charset="UTF-8">
    <title>Logistics Operations Enterprise Console</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>

    <div class="sidebar" id="sidebarMenu">
        <div class="logo-area">🚚 LMS ENTERPRISE</div>
        <ul>
            <li class="active" id="tab-dash"><a href="#" onclick="loadTab('dashboardArea', 'tab-dash')">📊 Command Dashboard</a></li>
            <li id="tab-order"><a href="#" onclick="loadTab('orderBookingArea', 'tab-order')">📦 Book Freight</a></li>
            <li id="tab-fleet"><a href="#" onclick="loadTab('fleetControlArea', 'tab-fleet')">🚛 Fleet Setup</a></li>
            <li id="tab-report"><a href="#" onclick="loadTab('auditReportsArea', 'tab-report')">📋 Audit Reports</a></li>
        </ul>
    </div>

    <div class="workspace" id="mainWorkspace">
        <div class="navbar">
            <button onclick="toggleSidebarMenu()" title="Toggle Sidebar">☰</button>
            <div class="nav-links">
                <span>Active Session: <b><span id="sessionUserDisplay">Guest</span></b> (<span id="sessionRoleDisplay">USER</span>)</span>
                <a href="#" onclick="toggleThemePalette()">🌗 Toggle Theme</a>
                <span style="color:#dc3545; font-weight:bold; margin-left:15px;" onclick="executeLogout()">Logout</span>
            </div>
        </div>

        <div id="dashboardArea" class="spa-tab active-tab">
            <h2>Operations Command Overview</h2>
            <div class="grid-3">
                <div class="card"><h3>Total Orders</h3><p style="font-size:32px; font-weight:bold; margin:10px 0 0 0;">${dashboard.totalOrders}</p></div>
                <div class="card" style="border-left: 4px solid #ffc107;"><h3>Unassigned Orders</h3><p style="font-size:32px; font-weight:bold; margin:10px 0 0 0;">${dashboard.pendingOrders}</p></div>
                <div class="card" style="border-left: 4px solid #198754;"><h3>Available Fleet Assets</h3><p style="font-size:32px; font-weight:bold; margin:10px 0 0 0;">${dashboard.availableTrucks} / ${dashboard.totalTrucks}</p></div>
            </div>

            <div class="card">
                <h3>📦 Real-Time Order Manifest Pipeline Progress Tracking</h3>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Cargo</th><th>Cost</th><th>Truck Status</th><th>Driver Status</th><th>Loading Milestone</th><th>Unloading Milestone</th>
                        </tr>
                    </thead>
                    <tbody id="livePipelineRows"></tbody>
                </table>
            </div>
        </div>

        <div id="orderBookingArea" class="spa-tab">
            <div class="grid-3" style="grid-template-columns: 2fr 1fr;">
                <div class="card">
                    <h2>📦 Book New Freight Order Manifest</h2>
                    <form id="orderForm">
                        <div class="form-group"><label class="required-field">Target Scheduling Date</label><input type="date" id="orderdate" required></div>
                        <div class="form-group"><label class="required-field">Commodity Name</label><input type="text" id="cargoName" required></div>
                        <div class="form-group"><label>Notes / Specifications</label><textarea id="cargoDescription" rows="2"></textarea></div>
                        <div class="form-group"><label class="required-field">Payload Weight (kg)</label><input type="number" id="cargowt" required></div>
                        <div class="form-group"><label class="required-field">Quantity Item Units</label><input type="number" id="cargocount" required></div>
                        <div class="form-group"><label class="required-field">Transit Travel Distance (Km)</label><input type="number" id="distanceKm" value="250" required></div>
                        <div class="form-group"><label class="required-field">Origin Address ID</label><input type="number" id="loadingid" required></div>
                        <div class="form-group"><label class="required-field">Destination Address ID</label><input type="number" id="unloadingid" required></div>
                        <button type="submit" class="btn-action">Dispatch Order Manifest</button>
                    </form>
                </div>
                <div class="card" style="border-top: 4px solid #198754;">
                    <h2>⚙️ Workflow Pipeline</h2>
                    <form id="lifecycleForm">
                        <div class="form-group"><label class="required-field">Target Order ID</label><input type="number" id="update_id" required></div>
                        <div class="form-group"><label class="required-field">Assign Carrier ID</label><input type="number" id="update_carrierid" required></div>
                        <div class="form-group"><label class="required-field">Transit Limit (Days)</label><input type="number" id="update_unloadingdays" value="2" required></div>
                        <button type="submit" class="btn-action" style="background:#198754;">Advance Pipeline Status</button>
                    </form>
                </div>
            </div>
        </div>

        <div id="fleetControlArea" class="spa-tab">
            <div class="grid-3">
                <div class="card">
                    <h2>👨‍✈️ Onboard Driver</h2>
                    <form id="driverForm">
                        <div class="form-group"><label class="required-field">Driver Name</label><input type="text" id="dname" required></div>
                        <div class="form-group"><label class="required-field">Contact Phone</label><input type="number" id="dcontact" required></div>
                        <button type="submit" class="btn-action">Add Driver Profile</button>
                    </form>
                </div>
                <div class="card">
                    <h2>🚛 Register Fleet Vehicle</h2>
                    <form id="truckForm">
                        <div class="form-group"><label class="required-field">Vehicle Model Name</label><input type="text" id="tname" required></div>
                        <div class="form-group"><label class="required-field">License Plate Number</label><input type="number" id="tnumber" required></div>
                        <div class="form-group"><label class="required-field">Max Carrying Payload (kg)</label><input type="number" id="tcapacity" required></div>
                        <button type="submit" class="btn-action">Register Truck Asset</button>
                    </form>
                </div>
            </div>
        </div>

        <div id="auditReportsArea" class="spa-tab">
            <div class="card">
                <h2>📋 Unified System Audit Manifest Reports</h2>
                
                <div id="adminControlsPanel" style="display:none; background:rgba(220,53,69,0.03); border:1px solid #dc3545; padding:20px; border-radius:6px; margin-bottom:20px;">
                    <strong style="color:#dc3545; display:block; margin-bottom:5px;">🔧 Administrative Fleet Resource Reallocation Overrides Panel</strong>
                    <span class="caution-text">⚠️ CRITICAL OVERRIDE BOUNDARY: All input parameters below are mandatory numbers.</span>
                    <div style="display:flex; gap:15px; margin-top:10px;">
                        <div style="flex:1;"><label class="required-field">ORDER ID</label><input type="number" id="override_oid" required></div>
                        <div style="flex:1;"><label class="required-field">NEW DRIVER ID</label><input type="number" id="override_did" required></div>
                        <div style="flex:1;"><label class="required-field">NEW TRUCK ID</label><input type="number" id="override_tid" required></div>
                        <div style="display:flex; align-items:flex-end;"><button onclick="executeAdminOverride()" style="background:#dc3545; color:#fff; border:none; padding:12px 20px; border-radius:4px; cursor:pointer; font-weight:bold; height:44px;">Apply Override</button></div>
                    </div>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th><th>Operator/User</th><th>Distance</th><th>Financial Cost</th><th>Payment Status</th>
                        </tr>
                    </thead>
                    <tbody id="masterAuditReportRows"></tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        const currentRole = localStorage.getItem("sessionRole") || "USER";
        const currentUser = localStorage.getItem("sessionUser") || "Guest";
        
        document.getElementById('sessionUserDisplay').innerText = currentUser;
        document.getElementById('sessionRoleDisplay').innerText = currentRole;

        if(currentRole === "ROLE_ADMIN" || currentRole === "ADMIN") {
            document.getElementById('adminControlsPanel').style.display = "block";
        }

        function getAuthHeaders() {
            return {
                'Authorization': 'Bearer ' + localStorage.getItem("authToken"),
                'Content-Type': 'application/json'
            };
        }

        function loadTab(tabId, navLiId) {
            document.querySelectorAll('.spa-tab').forEach(t => t.classList.remove('active-tab'));
            document.querySelectorAll('.sidebar ul li').forEach(li => li.classList.remove('active'));
            document.getElementById(tabId).classList.add('active-tab');
            if(navLiId) document.getElementById(navLiId).classList.add('active');
            if(tabId === 'dashboardArea' || tabId === 'auditReportsArea') syncTelemetryStream();
        }

        function toggleSidebarMenu() {
            document.getElementById('sidebarMenu').classList.toggle('collapsed');
            document.getElementById('mainWorkspace').classList.toggle('expanded');
        }

        function toggleThemePalette() {
            const current = document.documentElement.getAttribute('data-theme');
            document.documentElement.setAttribute('data-theme', current === 'dark' ? 'light' : 'dark');
        }

        function syncTelemetryStream() {
            fetch('${pageContext.request.contextPath}/api/admin/reports/all', {
                method: 'GET',
                headers: getAuthHeaders()
            })
            .then(res => {
                if(res.status === 403 || res.status === 401) {
                    alert("🔒 Security Expired: Access session token rejected.");
                    executeLogout();
                    return [];
                }
                return res.json();
            })
            .then(orders => {
                let pipelineHtml = ''; let reportsHtml = '';
                orders.forEach(o => {
                    pipelineHtml += `<tr>
                        <td><b>#\${o.id}</b></td><td>\${o.cargo ? o.cargo.name : 'N/A'}</td><td>₹\${o.cost}</td>
                        <td><span class="badge \${o.truckAssignmentStatus === 'PENDING' ? 'pending' : 'success'}">\${o.truckAssignmentStatus}</span></td>
                        <td><span class="badge \${o.driverAssignmentStatus === 'PENDING' ? 'pending' : 'success'}">\${o.driverAssignmentStatus}</span></td>
                        <td><span class="badge \${o.loadingStatus === 'PENDING' ? 'pending' : 'success'}">\${o.loadingStatus}</span></td>
                        <td><span class="badge \${o.unloadingStatus === 'PENDING' ? 'pending' : 'success'}">\${o.unloadingStatus}</span></td>
                    </tr>`;
                    reportsHtml += `<tr>
                        <td>#\${o.id}</td><td><b>\${o.bookedByUsername || 'Default Admin'}</b></td><td>\${o.distanceKm} Km</td><td>₹\${o.cost}</td>
                        <td><span class="badge \${o.paymentStatus === 'UNPAID' ? 'pending' : 'success'}">\${o.paymentStatus}</span></td>
                    </tr>`;
                });
                document.getElementById('livePipelineRows').innerHTML = pipelineHtml || '<tr><td colspan="7">No active operational manifests tracked.</td></tr>';
                document.getElementById('masterAuditReportRows').innerHTML = reportsHtml || '<tr><td colspan="5">No records returned.</td></tr>';
            });
        }

        function executeAdminOverride() {
            const oid = document.getElementById('override_oid').value;
            const did = document.getElementById('override_did').value;
            const tid = document.getElementById('override_tid').value;

            if(!oid || !did || !tid) {
                alert("⚠️ Validation Notice: Cannot submit incomplete override structures.");
                return;
            }

            fetch(`\${window.location.origin}/api/admin/order/override?orderId=\${oid}&driverId=\${did}&truckId=\${tid}`, {
                method: 'POST',
                headers: getAuthHeaders()
            })
            .then(res => {
                if(res.ok) { alert("🚨 Operational Override Applied!"); syncTelemetryStream(); }
                else alert("Override Blocked: Resource validation checks failed.");
            });
        }

        document.getElementById('orderForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const payload = {
                orderdate: document.getElementById('orderdate').value,
                cargoName: document.getElementById('cargoName').value,
                cargoDescription: document.getElementById('cargoDescription').value,
                cargowt: parseInt(document.getElementById('cargowt').value),
                cargocount: parseInt(document.getElementById('cargocount').value),
                distanceKm: parseInt(document.getElementById('distanceKm').value),
                loadingid: parseInt(document.getElementById('loadingid').value),
                unloadingid: parseInt(document.getElementById('unloadingid').value),
                username: currentUser
            };

            fetch('${pageContext.request.contextPath}/order', {
                method: 'POST',
                headers: getAuthHeaders(),
                body: JSON.stringify(payload)
            })
            .then(res => res.json())
            .then(data => {
                alert("🎉 Manifest Generated! Tracking Reference ID: " + data.data.id);
                document.getElementById('orderForm').reset();
                loadTab('dashboardArea', 'tab-dash');
            });
        });

        document.getElementById('lifecycleForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const id = document.getElementById('update_id').value;
            const cid = document.getElementById('update_carrierid').value;
            const days = document.getElementById('update_unloadingdays').value;

            fetch(`${pageContext.request.contextPath}/updateorder?id=\\${id}&carrierid=\\${cid}&unloadingdays=\\${days}`, {
                method: 'PUT',
                headers: getAuthHeaders()
            })
            .then(res => res.json())
            .then(data => {
                alert("⚙️ Phase Transition Notice: " + data.message);
                document.getElementById('lifecycleForm').reset();
                loadTab('dashboardArea', 'tab-dash');
            });
        });

        function executeLogout() {
            localStorage.clear();
            window.location.href = '${pageContext.request.contextPath}/';
        }

        window.onload = syncTelemetryStream;
    </script>
</body>
</html>