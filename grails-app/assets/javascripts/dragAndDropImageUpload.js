console.log("Hello world from the js file!  ")

//var user = ${user};
let user = $(this.user);

console.log(user.username);
let formData = new FormData();

// Javascript and JQuerry goes here !!

// Initialize all the events linked to the drag and drop in order to avoid event propagation

$(document).on('dragenter', '#dd-box', function() {
    $(this).css('border', '3px dashed red');
    return false;
});

$(document).on('dragover', '#dd-box', function(e) {
    e.preventDefault();
    e.stopPropagation();
    $(this).css('border', '3px dashed red');
    return false;
});

$(document).on('dragleave', '#dd-box', function(e) {
    e.preventDefault();
    e.stopPropagation();
    $(this).css('border', '3px dashed #BBBBBB');
    return false;
});

$(document).on('dragstart', '#dd-box', function(e) {
    console.log('dragstart');
    e.dataTransfer.setData('text/html', 'foo');
    e.dataTransfer.setData('draggable', '');
});

$(document).on('drop', '#dd-box', function(e) {
    e.preventDefault();
    e.stopPropagation();
    if(e.originalEvent.dataTransfer) {
        if(e.originalEvent.dataTransfer.files.length) {
            $(this).css('border', '3px dashed green');
            formData.append('profileImage', e.originalEvent.dataTransfer.files[0]);
            $(this).text(e.originalEvent.dataTransfer.files[0].name);
        }
    }
    else {
        $(this).css('border', '3px dashed #BBBBBB');
    }

    return false
});

$('#submit').on('click', function (e) {
    formData.append('username', $('#user-id').val());
    let val = $('#user-id').val();

    console.log(val);
    /*formData.append('password', $('#user-form-password').val());
    let request = new XMLHttpRequest();

    request.onreadystatechange = function() {
        if (request.readyState === 4) {
            console.log(request.response);
            console.log(JSON.parse(request.response).name);
            window.location = 'show/' + JSON.parse(request.response).name;
        }
    };

    request.open("POST", "/tp/user/save2");
    request.send(formData);*/
});