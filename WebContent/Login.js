/*
 This function take care about the login of new user to the server.
 */
function UserLogin()
{
    var username = document.getElementById('LoginPageUserName').value;
    var password = document.getElementById('LoginPagePassword').value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/userExist?username="+username+"&password="+password, false);
    xhr.onreadystatechange= function()
    {
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                // Saving the username to the document cookie for future using.
                document.cookie = "user:" + username;
                // Send the user to the main page of the application.
                window.location.href = "mainPage.html";
            }
            else
            {
                alert("incorrect username or password");
            }
        }
    };
    xhr.send("");
}

/*
 This function validate that the when a new user try to sign up to the system
 he enter a username, password and confirm the password.
 */
function UserSignUpValidate()
{
    if(SignUp.SignUpPageUserName.value != "")
    {
        if(SignUp.SignUpPagePassword.value != "")
        {
            if(SignUp.SignUpPagePasswordConfirm.value != "")
            {
                if(SignUp.SignUpPagePassword.value == SignUp.SignUpPagePasswordConfirm.value)
                {
                    UserSignUp();
                }
                else
                {
                    alert("The password doesnt match, try again!!!");
                }
            }
            else
            {
                alert("Write your password again!!!");
            }
        }
        else
        {
            alert("Insert password!!!");
        }
    }
    else
    {
        alert("Insert username!!!");
    }
}

/*
 This function take care about the sign up of a new user to the server.
 */
function UserSignUp()
{
    var username = document.getElementById('SignUpPageUserName').value;
    var password = document.getElementById('SignUpPagePassword').value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/addUser?username="+username+"&password="+password, false);
    xhr.onreadystatechange= function()
    {
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                // Saving the username to the document cookie for future using.
                document.cookie = "user:" + username;
                // Send the user to the main page of the application.
                window.location.href = "mainPage.html";
            }
            else
            {
                alert("incorrect username or password");
            }
        }
    };
    xhr.send("");
}