(function() {

  var pwd = document.getElementById('password');     // Get password input
  var chk = document.getElementById('showPwd'); // Get checkbox

  addEvent(chk, 'change', function(e) {         // When user clicks on checkbox
    var target = e.target || e.srcElement;      // Get that element
    try {                                       // Try the following code block
      if (target.checked) {                     // If the checkbox is checked
        pwd.type = 'text';                      // Set pwd type to text
      } else {                                  // Otherwise
        pwd.type = 'password';                  // Set pwd type to password
      }
    } catch(error) {                            // If this causes an error
      alert('This browser cannot switch type'); // Say that cannot switch type
    }
  });

}());

(function(){
  var username      = document.getElementById('username');
  var form      = document.getElementById('register');
  var password  = document.getElementById('password');
  var confirm_password  = document.getElementById('confirmpassword');
  var submit    = document.getElementById('submit');

  var submitted = false;
  var username_filled = true;
  var password_filled = true;
  var confirm_password_filled = true;
  submit.disabled = true;
  submit.className = 'disabled btn btn-info';
  console.log(submit.className);

  addEvent(username, 'input', function(e) {
    var target = e.target || e.srcElement;
    username_filled = false
    submit.disabled = submitted || !target.value || username_filled || password_filled || confirm_password_filled;
    submit.className = (submitted || !target.value || username_filled || password_filled || confirm_password_filled) ? 'disabled btn btn-info' : 'enabled btn btn-info';
  });

  addEvent(password, 'input', function(e) {
      var target = e.target || e.srcElement;
      password_filled = false;
      submit.disabled = submitted || !target.value || username_filled || password_filled || confirm_password_filled;
      submit.className = (submitted || !target.value || username_filled || password_filled || confirm_password_filled) ? 'disabled btn btn-info' : 'enabled btn btn-info';
    });

  addEvent(confirm_password, 'input', function(e) {
      var target = e.target || e.srcElement;
      confirm_password_filled = false;
      submit.disabled = submitted || !target.value || username_filled || password_filled || confirm_password_filled;
      submit.className = (submitted || !target.value || username_filled || password_filled || confirm_password_filled) ? 'disabled btn btn-info' : 'enabled btn btn-info';
  });
}());

function addEvent (el, event, callback) {
  if ('addEventListener' in el) {
    el.addEventListener(event, callback, false);
  } else {
    el['e' + event + callback] = callback;
    el[event + callback] = function () {
      el['e' + event + callback](window.event);
    };
    el.attachEvent('on' + event, el[event + callback]);
  }
}

function removeEvent(el, event, callback) {
  if ('removeEventListener' in el) {
    el.removeEventListener(event, callback, false);
  } else {
    el.detachEvent('on' + event, el[event + callback]);
    el[event + callback] = null;
    el['e' + event + callback] = null;
  }
}

