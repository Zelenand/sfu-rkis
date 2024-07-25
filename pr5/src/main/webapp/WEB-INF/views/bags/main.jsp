<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=cp1251" />
    <title>Список сумок</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
    />
</head>
<body id="form" style="background-color: #d7d7d7">

<div
        class="container col-lg-10  p-md-5 pt-5 text-center bg-white"
>
    <div class="container text-start ">
        <div class="row">
            <div class="col-4 p-0 pb-2">
                <h1 class="mb-3 ">Список сумок</h1>

                <form th:action="@{/bags}" th:method="get" class="input-group mb-3">
                    <input class="form-control" th:value="${maxPrice}" type="number" min="0" for="price" placeholder="максимальная цена" id="price" name="price" required>
                    <button class="btn btn-outline-secondary" type="submit" id="button-price" >Отфильтровать</button>
                </form>
            </div>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Брэнд</th>
            <th>Материал</th>
            <th>Тип</th>
            <th>Цена</th>
            <th>Объём</th>
            <th style="border: none"></th>
            <th style="border-left: none"></th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="bag: ${bags}">
            <td th:text = "${bag.brand}"></td>
            <td th:text = "${bag.material}"></td>
            <td th:text = "${bag.type}"></td>
            <td th:text = "${bag.cost}"></td>
            <td th:text = "${bag.volume}"></td>
            <td>
                <a th:href="@{'bags/edit/' + ${bag.id}}" type="submit" class="btn btn-sm btn-dark">редактировать</a>
            </td>
            <td>
                <form th:action="@{'/bags/delete/' + ${bag.id}}" th:method="post" class="d-inline">
                    <button type="submit" class="btn btn-sm btn-danger">
                        &nbsp; x &nbsp;
                    </button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <a href="/bags/add" class="btn btn-sm btn-success">
        Добавить запись
    </a>
    <form th:action="@{/bags/add-test-data}" th:method="post" class="d-inline">
        <button type="submit" class="mx-2 btn btn-sm btn-secondary">
            Заполнить таблицу данными-заполнителями
        </button>
    </form>



</div>
</body>
</html>
</html>
