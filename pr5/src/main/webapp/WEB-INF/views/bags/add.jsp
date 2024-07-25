<!DOCTYPE html>
<html lang="en">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=cp1251" />
    <title>Добавление записи</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
    />
</head>
<body id="form" style="background-color: #d7d7d7">
<nav
        class="navbar sticky-top navbar-expand justify-content-md-between px-md-5 py-3 justify-content-center bg-light"
>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="/bags">Назад</a>
    </div>
</nav>
<div
        class="container shadow col-lg-8 p-md-5 pt-5 text-start bg-white "
>
    <div class="mb-4">
        <h2>Создание записи</h2>
    </div>
    <form action="#" th:action="@{/bags}" th:object="${bag}" method="post">
        <div class="form-group mb-3 ">
            <label for="brand" class="mb-2">Брэнд</label>
            <input type="text" class="form-control" id="brand" name="brand" th:field="*{brand}" required>
            <div style="color:red" th:if="${#fields.hasErrors('brand')}" th:errors="*{brand}">Error</div>
        </div>
        <div class="form-group mb-3 ">
            <label for="material" class="mb-2">Материал</label>
            <input type="text" class="form-control"  id="material" name="material" th:field="*{material}" required>
            <div style="color:red" th:if="${#fields.hasErrors('material')}" th:errors="*{material}">Error</div>
        </div>
        <div class="form-group mb-3 ">
            <label for="type" class="mb-2">Тип</label>
            <input type="text" class="form-control" id="type" name="type" th:field="*{type}" required>
            <div style="color:red" th:if="${#fields.hasErrors('type')}" th:errors="*{type}">Error</div>
        </div>
        <div class="form-group mb-3 ">
            <label for="cost" class="mb-2">Цена</label>
            <input inputmode="decimal" type="numeric" class="form-control" id="cost" name="cost" th:field="*{cost}" required>
            <div style="color:red" th:if="${#fields.hasErrors('cost')}" th:errors="*{cost}">Error</div>
        </div>
        <div class="form-group mb-3 ">
            <label for="volume" class="mb-2">Объём</label>
            <input inputmode="numeric" type="decimal" class="form-control" id="volume" name="volume" th:field="*{volume}" required>
            <div style="color:red" th:if="${#fields.hasErrors('volume')}" th:errors="*{volume}">Error</div>
        </div>
        <button class="test btn btn-secondary py-2" type="submit">Добавить</button>
    </form>
</div>
</body>
</html>
</html>
