for (var i = 0; i < $('div[id^="comment-content"]').length; i++) {
        var md = window.markdownit();
        var emoji = window.markdownit().use(window.markdownitEmoji);
        var s = 'comment-content' + i;
        var markdown = $('#' + s).html();
        var html = md.render(markdown);
        html = emoji.render(markdown);
        $('#' + s).html(html);
}

for (var i = 0; i < $('div[id^="mycomment-content"]').length; i++) {
        var md = window.markdownit();
        var emoji = window.markdownit().use(window.markdownitEmoji);
        var s = 'mycomment-content' + i;
        var markdown = $('#' + s).html();
        var html = md.render(markdown);
        html = emoji.render(markdown);
        $('#' + s).html(html);
}

var mdPost = window.markdownit();
var emojiPost = window.markdownit().use(window.markdownitEmoji);
var markdownPost = $('#post-content').html();
var html = mdPost.render(markdownPost);
html = emojiPost.render(markdownPost);
$('#post-content').html(html);
