<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&family=Roboto:wght@700&display=swap"
        rel="stylesheet">
    <title>Favery</title>
</head>

<body>
    <nav>
        <ul class="navbar">
            <div class="leftend">
                <li><img src="assets/logo.png" alt="Logo" width="75" height="75"></li>
                <li>Favery</li>
            </div>
            <div class="rightend">
                <button class="btn" onclick="showModal('signinModal')">Sign In</button>
                <button class="btn" onclick="showModal('signupModal')">Sign Up</button>
            </div>
        </ul>
    </nav>

    <div class="contain">
        <div class="title-text">
            <h1>Order Food</h1>
            <h1 class="typewriter">
                <p id="demo"></p>
            </h1>
            <div class="social-icons">
                <p>Follow us on social media</p>
                <a href="#"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAcBJREFUSEvllk1OxDAMhT03gZMABwEBFwFOws9F+NmyZQ+cBPqN/JBJnTZtFhUi0qgznfg9+9lxvLON1m4jXvsTxAdmxmduPc1t4P+5iK/N7MjMjlvAfM+HP++HJ/bpqhFDdBsiFJieU35EJ9l/YmYjuxrxu5Mi280QcZN87o3SgeM4Aelh6WlGjMG5k+Ht0nXqKXxx1SC/M7OLCJQRK1q8bJE24kH64C8gwv4xizoj/nLDWhqQEkUoOoA/PSK+nw2yUlSsSyclkJHcJTigbCSnmcxU6VWiPcCqYpwC983MXh0PXPB+aqUkJh9IkxFHUgpOaSByyFi8L48QeOCuIpYSgP8CcEI5jDPkNp6CLmJFO6rOILtOQxl1F7FAiQbybNXS1EWcGhfs5BkHS1W6iDeTOhZXJrdkzoqvK2IAJSXfkfM59Gad7ew4qRNOHqepBqKOlTWQ2hnmfdqCa50rvVFChEhL48AZIkeBWl9PW3DWj1squPXGUnpmezWAquBav24lZZ+CaLoWkQ8DnmsGAQixXTwIyFDk/O4ZfcrevVdsatiL9+6SYU+Orhr2yjy2jrbYNc1nc+PtkkJatPf/EX8DIXGOHzTONr8AAAAASUVORK5CYII=" /></a>
                <a href="#"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAMRJREFUSEvtltENgzAMRI9NyibtJmWStpPQTYBJYJT2UCNZESQmNERIzg8fIL/c+YxcodCpCnFxevD159zkPVcN3aOYsAcAB3UQwutYC1PBFwDjSvGsYEIJXzrZwEtqewCDuMUzh9XsaScKqxT6F0np8R1AKwpR7S2m0MBaq0Mp9l18A2hi1ucAv77Qv6V6i+JiYCacSQ8erdWyiI2T/UBiuZrfW7g0Ntk4HTZOXH3kvsUNxK21mlYlp1pdPPRhyjidG/wBFwwxH4oeX7wAAAAASUVORK5CYII=" /></a>
                <a href="#"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAAXNSR0IArs4c6QAAAWtJREFUSEvtlu1tAjEMhl8moUxSmIQySdtJCpNAJymj0AfFVQhO4vtQ+YMldOIu8fthx3cLPSgWD8LVE/jfnJ/L6g9JW0kvkk6SDul6Tkreiv9ujY+SdpJsU8sFgL5+k66dRRD4ToR4vMrXlIpJ9JPYRcAh6YHmGAj4TEIgc40SGEtQQLCoBW4ke31hzjUVwx4VecB271hPXd97qGkfAv7Ueoq5h9WoKYONZhkqosDsYe1NeF2dd2hLEOAewXJPCJga27GIJA04fe0TStVU7NU4kry1ZlPWd2iNxxCgHDfdbEm8GmMxnT2H1TQkiu+iNjJtInGdQsC1uWY192kyzugU0KrNLeA57K6qbQHzbAq4e3bzQkdeixwxzjb2R6LaUEOBeWlEQRkUDIxulIpRZ528HAAIkDuhagw8YGw1Al3maRyGVEasBthe8K+ZCxwR+/F1cTeDI0x7XR3NMWpdpKtHJe5tegL3HJrt+QXBlEMf+R+PgwAAAABJRU5ErkJggg==" /></a>
            </div>
        </div>
        <div class="db-image">
            <img src="assets/Delivery Boy.png" alt="Delivery Boy">
        </div>
    </div>

    <div class="modal-overlay" id="signupModal">
        <div class="modal-content">
            <button class="modal-close" onclick="closeModal()">×</button>
            <h2>Sign Up</h2>
            <form id="signupForm" action="register">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>

                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phoneNumber" placeholder="Enter your phone number" required>

                <label for="address">Address:</label>
                <textarea id="address" name="address" placeholder="Enter your address" required></textarea>

                <input type="submit" value="Sign Up" class="btn">
            </form>
        </div>
    </div>

    <div class="modal-overlay" id="signinModal">
        <div class="modal-content">
            <button class="modal-close" onclick="closeModal()">×</button>
            <h2>Sign In</h2>
            <form id="signinForm" action="login">
                <label for="signinEmail">Email:</label>
                <input type="email" id="signinEmail" name="email" placeholder="Enter your email" required>

                <label for="signinPassword">Password:</label>
                <input type="password" id="signinPassword" name="password" placeholder="Enter your password"
                    required>

                <input type="submit" value="Sign In" class="btn">
            </form>
        </div>
    </div>

    <script src="js/script.js"></script>
</body>

</html>