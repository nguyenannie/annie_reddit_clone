for (var i = 0; i < $('div[id^="comment-content"]').length; i++) {
        var md = window.markdownit();
        var s = 'comment-content' + i;
        var markdown = $('#' + s).html();
        var html = md.render(markdown);
        $('#' + s).html(html);
}
for (var i = 0; i < $('div[id^="mycomment-content"]').length; i++) {
        var md = window.markdownit();
        var s = 'mycomment-content' + i;
        var markdown = $('#' + s).html();
        var html = md.render(markdown);
        $('#' + s).html(html);
}