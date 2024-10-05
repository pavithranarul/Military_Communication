<%@ page session="true" %>
<%@ page import="model.User" %>
<%
    // Check if the user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // Redirect to login page if the user is not logged in
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        /* Basic body and container styling */
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
        }

        /* Styling the form */
        .message-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        .message-form p {
            color: green;
            font-size: 16px;
        }

        .message-form textarea, .message-form input[type="text"], .message-form input[type="number"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .message-form input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        .message-form input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="message-form">
        <%-- Display success message if any --%>
        <% if (request.getParameter("message") != null) { %>
            <p>Message sent successfully!</p>
        <% } %>

        <!-- Form for sending a message -->
        <form action="sendMessage" method="post">
            <textarea name="messageContent" placeholder="Enter message" required></textarea><br>
            Receiver ID: <input type="number" name="receiverId" required><br>
            <!-- Sender ID is auto-filled and hidden -->
            <input type="hidden" name="senderId" value="<%= user.getId() %>">
            <input type="submit" value="Send Message">
        </form>
    </div>
</div>

</body>
</html>
