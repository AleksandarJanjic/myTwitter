$( document ).ready(function() {
    getMessages();
    $("#messageSubmit").submit(function(event){
            event.preventDefault();
            let username = $("#username").html();
            let message = $("input[name='messageContent']").val();
            let iduser;
            let userID = $.post("/user/getIDbyName", {username:username}, function(data) {
                iduser = data.id;
                let posting = $.post("/message/postMessage", {username: username, userID: iduser, message:message}, function(){
                    $("#messages").empty();
                    getMessages();
                    $("input[name='messageContent']").val().empty();
                });
            });
        });
    function getMessages() {
    $.ajax({
        type: "POST",
        url: "/message/getMessages"
    }).done (function(data) {
        for(i = 0; i<data.length; i++ ) {
            let username = data[i].username;
            let content = data[i].content;
            let datetime = data[i].messageTime;
            let likes = data[i].totalLikes;
            let id = data[i].messageid;
            let div = document.createElement("div");
            let usernameElement = document.createElement("p");
            usernameElement.innerHTML = username;
            $(div).append(usernameElement);
            let contentElement = document.createElement("p");
            contentElement.innerHTML = content;
            $(div).append(contentElement);
            let datetimeElement = document.createElement("p");
            datetimeElement.innerHTML = datetime;
            $(div).append(datetimeElement);
            let totalLikesElement = document.createElement("a");
            totalLikesElement.innerHTML = likes + " likes ";
            totalLikesElement.setAttribute("id", id);
            totalLikesElement.setAttribute("title", "Who liked this message");
            $(div).append(totalLikesElement);
            let likeButton = document.createElement("button");
            likeButton.setAttribute("class", "likebutton")
            likeButton.setAttribute("id", id);
            likeButton.innerHTML = "Like";
            $(div).append(likeButton);
            let commentFormDiv = document.createElement("div");
            let commentForm = document.createElement("form");
            commentForm.setAttribute("id", id);
            let commentField = document.createElement("input");
            commentField.setAttribute("type", "text");
            commentField.setAttribute("name", "comment");
            commentField.setAttribute("id", id);
            let commentSubmit = document.createElement("input");
            commentSubmit.setAttribute("type", "submit");
            commentSubmit.setAttribute("value", "Comment");
            commentSubmit.setAttribute("id", id);
            $(commentForm).append(commentField);
            $(commentForm).append(commentSubmit);
            $(commentFormDiv).append(commentForm);
            $(div).append(commentFormDiv);
            let commentDiv = document.createElement("div");
            $.post("/comment/getComments", {messageID:id}, function(data){
                for(i = 0; i<data.length; i++) {
                    let comment = document.createElement("p");
                    comment.innerHTML = data[i].content;
                    $(commentDiv).append(comment);
                }
            })
            $(likeButton).on("click", function(){
                   let messageID = this.id;
                   let like = $.post("/message/likeMessage", {messageID: messageID}, function(){
                        $("#messages").empty();
                        getMessages();
                   });
                });
            $(commentSubmit).on("click", function(event){
                event.preventDefault();
                let messageID = this.id;
                let selector = "#";
                let final = selector.concat(messageID);
                let content = $(commentField).filter(final).val();
                let comment = $.post("/comment/postComment", {messageID: messageID, content: content});
                console.log(messageID);
            })
            $(totalLikesElement).on("click", function() {
                    let messageID = this.id;
                    let likers = $.post("/likes/all", {messageID: messageID}, function(data) {
                        let base = $("<div>");
                        for (i = 0; i<data.length; i++) {
                            let username = data[i].name;
                            let user = $("<p>");
                            base.append(username);
                            let br = $("<br>");
                            base.append(br);
                        }
                        base.dialog({
                            close: function() {
                                $(this).dialog("destroy").remove();
                            }
                        })
                    })
                 })
            $(div).append(commentDiv);
            $("#messages").append(div);
        }
    });
    }
});