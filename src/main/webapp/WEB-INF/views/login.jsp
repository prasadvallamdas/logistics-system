<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logistics Gateway Portal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

    <div class="card">
        <input type="checkbox" id="panelToggle" class="toggle-trigger" />
        <div class="card-bg"></div>

        <div class="hero signup-hero">
            <h2>Welcome Back!</h2>
            <p>To stay connected with your active fleet operations metrics, please sign in with your credentials.</p>
            <label for="panelToggle" class="switch-label" onclick="clearForms()">SIGN IN</label>
        </div>

        <div class="form-panel signup-form">
            <h2>Create Account</h2>
            <p>Register to start dispatching freight manifests.</p>
            <span class="caution-text">⚠️ All fields below are strictly mandatory.</span>
            <form id="registrationForm" style="width: 100%;">
                <div class="input-wrapper">
                    <label for="regName" class="required-field" style="font-size:11px; font-weight:bold; color:var(--text-muted);">FULL NAME</label>
                    <input type="text" id="regName" placeholder="John Doe" required>
                </div>
                <div class="input-wrapper">
                    <label for="regMail" class="required-field" style="font-size:11px; font-weight:bold; color:var(--text-muted);">EMAIL ADDRESS</label>
                    <input type="email" id="regMail" placeholder="john@company.com" required>
                </div>
                <div class="input-wrapper">
                    <label for="regPass" class="required-field" style="font-size:11px; font-weight:bold; color:var(--text-muted);">SECURITY PASSWORD</label>
                    <input type="password" id="regPass" placeholder="Minimum 6 characters" minlength="6" required>
                </div>
                <button type="submit" class="btn-submit">Register User Profile</button>
            </form>
        </div>

        <div class="hero signin-hero">
            <h2>Hello, Friend!</h2>
            <p>Enter your professional credentials and start tracking freight modules in real time.</p>
            <label for="panelToggle" class="switch-label" onclick="clearForms()">SIGN UP</label>
        </div>

        <div class="form-panel signin-form">
            <h2 id="loginHeaderTitle">Operator Sign In</h2>
            <p id="loginSubtitle">Select your system access tier below.</p>
            
            <div class="role-switch-container">
                <button type="button" id="roleUserBtn" class="role-btn active-role" onclick="setLoginTier('USER')">👤 Client User</button>
                <button type="button" id="roleAdminBtn" class="role-btn" onclick="setLoginTier('ADMIN')">🛠️ System Admin</button>
            </div>

            <form id="authLoginForm" style="width: 100%;">
                <input type="hidden" id="loginRoleField" name="role" value="USER">
                <span class="caution-text">⚠️ Fields marked with an asterisk (*) are mandatory.</span>

                <div class="input-wrapper">
                    <label id="idFieldLabel" for="loginIdField" class="required-field" style="font-size:11px; font-weight:bold; color:var(--text-muted); display:block; margin-bottom:5px;">EMAIL ADDRESS</label>
                    <input type="text" id="loginIdField" placeholder="Email Address" required>
                </div>
                <div class="input-wrapper">
                    <label for="loginPasswordField" class="required-field" style="font-size:11px; font-weight:bold; color:var(--text-muted); display:block; margin-bottom:5px;">SECURITY PASSWORD</label>
                    <input type="password" id="loginPasswordField" placeholder="Password Handle" required>
                </div>
                <button type="submit" class="btn-submit">Unlock System Console</button>
            </form>
        </div>
    </div>

    <script>
        let selectedTier = "USER";

        function setLoginTier(tier) {
            selectedTier = tier;
            document.getElementById('loginRoleField').value = tier;
            const userBtn = document.getElementById('roleUserBtn');
            const adminBtn = document.getElementById('roleAdminBtn');
            const title = document.getElementById('loginHeaderTitle');
            const placeholder = document.getElementById('loginIdField');
            const label = document.getElementById('idFieldLabel');

            if (tier === 'ADMIN') {
                userBtn.classList.remove('active-role');
                adminBtn.classList.add('active-role');
                title.innerText = "Administrative Access";
                placeholder.placeholder = "Admin Username or ID";
                label.innerText = "ADMIN IDENTIFIER";
            } else {
                adminBtn.classList.remove('active-role');
                userBtn.classList.add('active-role');
                title.innerText = "Operator Sign In";
                placeholder.placeholder = "Email Address";
                label.innerText = "EMAIL ADDRESS";
            }
        }

        function clearForms() {
            document.getElementById('registrationForm').reset();
            document.getElementById('authLoginForm').reset();
            setLoginTier('USER');
        }

        // AJAX JWT LOGIN HANDLER IMPLEMENTATION
        document.getElementById('authLoginForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const payload = {
                username: document.getElementById('loginIdField').value,
                password: document.getElementById('loginPasswordField').value,
                role: document.getElementById('loginRoleField').value
            };

            fetch('${pageContext.request.contextPath}/api/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            })
            .then(res => {
                if (!res.ok) return res.json().then(err => { throw new Error(err.message); });
                return res.json();
            })
            .then(data => {
                localStorage.setItem("authToken", data.token);
                localStorage.setItem("sessionUser", data.username);
                localStorage.setItem("sessionRole", data.role);
                alert("🔓 Access Granted: Security token issued.");
                window.location.href = `${pageContext.request.contextPath}/dashboard?username=` + encodeURIComponent(data.username) + `&role=` + data.role;
            })
            .catch(err => alert("🔒 Access Denied: " + err.message));
        });

        document.getElementById('registrationForm').addEventListener('submit', function(e) {
            e.preventDefault();
            const payload = {
                name: document.getElementById('regName').value,
                mail: document.getElementById('regMail').value,
                password: document.getElementById('regPass').value,
                role: "USER"
            };

            fetch('${pageContext.request.contextPath}/saveuser', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            })
            .then(res => res.json())
            .then(data => {
                if (data.status === 406) {
                    alert("Registration Rejected: " + data.message);
                } else {
                    alert("🎉 Success: " + data.message);
                    document.getElementById('panelToggle').checked = false;
                    clearForms();
                }
            });
        });
    </script>
</body>
</html>