<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/ejs_production.js"></script>

    <script type="text/javascript">
        function addItem2(form) {
            $.post('/todo/create.json', $(form).serialize(),function(data) {
                var todoItemLI = $(new EJS({url: '/static/ejs/todo.ejs'}).render(data)).data("todoID", data.id);
              $('#todoList').append(todoItemLI);
            });
        }

        $(".todoDelete").live("click", function(evt){
            var id = $(evt.target).parents("li").data("todoID");
            $.post('/todo/delete.json', {id:id},function(data) {
                $(evt.target).parents("li").remove();
            });
        });
        $(".todoUpdate").live("submit", function(evt){
            var form = evt.target;
            var data = {id:form.id.value, name:form.name.value};
            $.post("/todo/update.json", $(form).serialize(),function() {
                var todoItemLI = $(new EJS({url: '/static/ejs/todo.ejs'}).render(data)).data("todoID", data.id);
                $('#todoItem' + data.id).replaceWith(todoItemLI);
            });
        });

        function appendItem(data) {
            var todoItemLI = $(new EJS({url: '/static/ejs/todo.ejs'}).render(data)).data("todoID", data.id);
            $('#todoList').append(todoItemLI);
        }
    </script>
</head>
<body>
Hello ${sessionScope.userName} <a href="/user/logout">Logout</a>

<h1>ToDo</h1>
<ul id="todoList">
    <c:forEach var='item' items='${todoItemList}'>
        <script type="text/javascript">
            appendItem({id:${item.id}, name:'${item.name}'})
        </script>
    </c:forEach>
</ul>

<form action="/todo/create" onsubmit="addItem2(this); return false;">
    New todo:
    <input type="text" name="name"/>
    <input type="submit" value="Add"/>
</form>

</body>
</html>