(function(){
  var create_subreddit_form  = document.getElementById('create_subreddit_form');
  var name  = document.getElementById('name');
  var description = document.getElementById('description');
  var create_subreddit_submit = document.getElementById('create_subreddit_submit');

  var submitted = false;
  var name_filled = true;
  var description_filled = true;
  create_subreddit_submit.disabled = true;
  create_subreddit_submit.className = 'disabled btn btn-info';

  addEvent(name, 'input', function(e) {
    var target = e.target || e.srcElement;
    name_filled = false
    create_subreddit_submit.disabled = submitted || !target.value || name_filled || description_filled;
    create_subreddit_submit.className = (submitted || !target.value || name_filled || description_filled) ? 'disabled btn btn-info' : 'enabled btn btn-info';
  });

  addEvent(description, 'input', function(e) {
      var target = e.target || e.srcElement;
      description_filled = false;
      create_subreddit_submit.disabled = submitted || !target.value || name_filled || description_filled;
      create_subreddit_submit.className = (submitted || !target.value || name_filled || description_filled) ? 'disabled btn btn-info' : 'enabled btn btn-info';
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
