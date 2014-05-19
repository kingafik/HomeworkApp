/*
 This function get new homework parameters from the user
 and send them by the query string to the server,
 for creating new task for the current user.
 */
function AddHomeWork()
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var taskName = document.getElementById('NewTaskName').value;
    var course = $("#NewSelectCourse option:selected").text();
    var type = $("#NewSelectType option:selected").val();
    var priority = $("#NewPriority :radio:checked").val();
    var Date = document.getElementById('NewDueDate').value;
    var additionalInfo = document.getElementById('NewHomeworkAdditionalInfo').value;
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/addHomework?taskName="
        +taskName+"&priority="+priority+"&type="+type+"&course="+course+"&username="+username+"&date="+Date+"&info="+additionalInfo, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                passFlag = true;
                alert("Edit HomeWork Succeed");
            }
        }
        if(passFlag != true)
        {
            alert("Add HomeWork Failed");
        }
    };
    xhr.send(null);
}

/*
 This function get new homework parameters from the user
 and send them by the query string to the server,
 for editing an existing task for the current user.
 */
function EditHomeWork()
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var taskName = document.getElementById('EditTaskName').value;
    var course = $("#EditSelectCourse option:selected").text();
    var type = $("#EditSelectType option:selected").val();
    var priority = $("#EditPriority :radio:checked").val();
    var Date = document.getElementById('EditDueDate').value;
    var additionalInfo = document.getElementById('EditHomeworkAdditionalInfo').value;
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/editHomework?taskName="
        +taskName+"&priority="+priority+"&type="+type+"&course="+course+"&username="+username+"&date="+Date+"&info="+additionalInfo, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                passFlag = true;
                alert("Edit HomeWork Succeed");
            }
        }
        if(passFlag != true)
        {
            alert("Edit HomeWork Failed");
        }
    };
    xhr.send(null);
}

/*
 This function get new course parameters from the user
 and send them by the query string to the server,
 for creating new course for the current user.
 */
function AddCourse()
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var courseName = document.getElementById('NewCourseName').value;
    var building = document.getElementById('NewBuilding').value;
    var room = document.getElementById('NewRoom').value;
    var lecturer = document.getElementById('NewLecturer').value;
    var additionalInfo = document.getElementById('NewCourseAdditionalInfo').value;
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/addCourse?courseName="
        +courseName+"&lecturer="+lecturer+"&building="+building+"&room="+room+"&info="+additionalInfo+"&username="+username, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                passFlag = true;
                alert("Edit HomeWork Succeed");
            }
        }
        if(passFlag != true)
        {
            alert("Add Course Failed");
        }
    };
    xhr.send(null);
}

/*
 This function get new course parameters from the user
 and send them by the query string to the server,
 for editing an existing course for the current user.
 */
function EditCourse()
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var courseName = document.getElementById('EditCourseName').value;
    var building = document.getElementById('EditBuilding').value;
    var room = document.getElementById('EditRoom').value;
    var lecturer = document.getElementById('EditLecturer').value;
    var additionalInfo = document.getElementById('EditCourseAdditionalInfo').value;
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/editCourse?courseName="
        +courseName+"&lecturer="+lecturer+"&building="+building+"&room="+room+"&info="+additionalInfo+"&username="+username, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                passFlag = true;
                alert("Edit HomeWork Succeed");
            }
        }
        if(passFlag != true)
        {
            alert("Edit Course Failed");
        }
    };
    xhr.send(null);
}

/*
 This function getting all the user courses from the server,
 set them into coursesArray and return the array.
 */
function GetCourses()
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var coursesArray = [];
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/getCourses?username="+username, false);
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
                for(var i in json.object)
                {
                    // Adding every object in the json object to the coursesArray.
                    coursesArray.push(json.object[i]);
                }
            }
        }

    };
    xhr.send(null);

    return coursesArray;
}

/*
 This function getting all the user homeworks from the server,
 set them into homeworksArray and return the array.
 */
function GetHomeworks()
{
    //Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var homeworksArray = [];
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/getHomeworks?username="+username, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                for(var i in json.object)
                {
                    // Adding every object in the json object to the homeworksArray.
                    homeworksArray.push(json.object[i]);
                }
            }
        }
    };
    xhr.send(null);

    return homeworksArray;
}

/*
 This function setting a select options with courses dynamically.
 */
function setSelectWithCourses(id)
{
    // Getting the user courses from the server.
    var coursesArray = GetCourses();
    $.each(coursesArray, function(index, value) {
        $("#"+id).append($("<option />").val(value.courseName).text(value.courseName));
    });
    $("#"+id).listview( "refresh" );
}

/*
 This function setting the listview <li>'s dynamically with the user's homeworks before the homework page show.
 */
$(document).on('pagebeforeshow', '#HomeWork', function()
{
    // Getting the user homeworks from the server.
    var homeworksArray = GetHomeworks();
    $("#userHomework").empty();
    $.each(homeworksArray, function(index, value) {
        // Every <li> in the listview have an onclick function that by click pass the user to another page that show the homework details
        // and the user can edit it from this page.
        $('<li onclick=\"fillHomeworkDetails(\''+ value.taskName +'\',\'' + value.courseName +'\',\'' + value.priority +'\',\'' + value.type +'\',\'' + value.date +'\',\'' + value.info +'\')\">')
            .append('<a style="text-align:center" href="#EditHomeWork">' + value.taskName +'</a> <a onclick="deleteHomework(\'' + value.taskName + '\')"></a>').appendTo("#userHomework");
    });
    $("#userHomework").listview("refresh");
});

/*
 This function setting the listview <li>'s dynamically with the user's courses before the course page show.
 */
$(document).on('pagebeforeshow', '#Course', function()
{
    var coursesArray = GetCourses();
    //var coursesArray = ["Course 1","Course 2","Course 3"];
    $("#userCourses").empty();
    $.each(coursesArray, function(index, value) {
        // Every <li> in the listview have an onclick function that by click pass the user to another page that show the course details
        // and the user can edit it from this page.
        $('<li onclick=\"fillCourseDetails(\''+ value.courseName +'\',\'' + value.building +'\',\'' + value.room +'\',\'' + value.lecturer +'\',\'' + value.info +'\')\">')
            .append('<a style="text-align:center" href="#EditCourse">' + value.courseName +'</a> <a onclick="deleteCourse(\'' + value.courseName + '\')"></a>').appendTo("#userCourses");
    });
    $("#userCourses").listview().listview('refresh');
});

/*
 This function get the about page information from the server and set it to the about page before show.
 */
$(document).on('pagebeforeshow', '#About', function()
{
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/about", false);
    xhr.onreadystatechange= function()
    {
        // Checking if the xhr object is ready and the xhr object status is OK
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            document.getElementById('aboutPageResponse').innerHTML = xhr.responseText;
        }
    };
    xhr.send(null);
});

/*
 This function fill the course details to the EditCoursePage form
 */
function fillCourseDetails(courseName, building, room, lecturer, info)
{
    EditCourseForm.EditCourseName.value = courseName;
    $('#EditCourseName').textinput({ disabled: true });
    EditCourseForm.EditBuilding.value = building;
    EditCourseForm.EditRoom.value = room;
    EditCourseForm.EditLecturer.value = lecturer;
    EditCourseForm.EditCourseAdditionalInfo.value = info;
}

/*
 This function fill the homework details to the EditHomeworkPage form
 */
function fillHomeworkDetails(taskName, course, priority, type, date, info)
{
    // Setting the select options with the user courses dynamically.
    setSelectWithCourses('EditSelectCourse');
    EditHomeworkForm.EditTaskName.value = taskName;
    $('#EditTaskName').textinput({ disabled: true });
    EditHomeworkForm.EditSelectCourse.value = course;
    $('#EditSelectCourse').selectmenu({ disabled: true });
    $('#EditSelectCourse').selectmenu('refresh', true);
    // Initialize the radio buttons check box.
    $("input[type='radio']").checkboxradio();
    // Clear the selection from all the radio buttons.
    $('input:radio[name=EditPriority]').prop("checked",false).checkboxradio( "refresh" );
    // Set selected to the radio button with the correct priority.
    switch(priority)
    {
        case 'HIGH':
            $('input:radio[name=EditPriority]:nth(0)').prop("checked",true).checkboxradio( "refresh" );
            break;
        case 'MEDIUM':
            $('input:radio[name=EditPriority]:nth(1)').prop("checked",true).checkboxradio( "refresh" );
            break;
        case 'LOW':
            $('input:radio[name=EditPriority]:nth(2)').prop("checked",true).checkboxradio( "refresh" );
            break;
        default:
            break;
    }
    // Initialize the select menu.
    $('[name=EditSelectType]').selectmenu();
    // Set the current value of the selection.
    $('[name=EditSelectType]').val(type).selectmenu('refresh', true);
    EditHomeworkForm.EditDueDate.value = date;
    EditHomeworkForm.EditHomeworkAdditionalInfo = info;
}

function deleteHomework(taskName)
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var courseName = document.getElementById('NewCourseName').value;
    var taskName = document.getElementById('NewTaskName').value;
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/deleteHomework?courseName="
        +courseName+"taskName="+taskName+"&username="+username, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                passFlag = true;
                alert("The task: " + taskName + " deleted");
            }
        }
        if(passFlag != true)
        {
            alert("Failed to delete " + taskName);
        }
    };
    xhr.send(null);
}

function deleteCourse(courseName)
{
    // Getting the username from the browser cookies. This info saved in the login page.
    var username = document.cookie.valueOf("user:").split(":", 2)[1];
    var courseName = document.getElementById('NewCourseName').value;
    var xhr = new XMLHttpRequest();

    xhr.open("GET", "http://env-7901845.whelastic.net/ServletController/deleteCourse?courseName="
        +courseName+"&username="+username, false);
    xhr.onreadystatechange= function()
    {
        var passFlag = false;
        // Checking if the xhr object is ready and the xhr object status is OK.
        if (xhr.readyState == 4 && xhr.status == 200)
        {
            // Creating a json object and parsing the xhr.responseText into it for getting all the parameters from the server.
            var json = JSON.parse(xhr.responseText);
            // Checking if the transaction succeed in the server.
            if(json.result == true)
            {
                passFlag = true;
                alert("The course: " + courseName + " deleted");
            }
        }
        if(passFlag != true)
        {
            alert("Failed to delete " + courseName);
        }
    };
    xhr.send(null);
}
