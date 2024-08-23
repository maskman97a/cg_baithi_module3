<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý thuê trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div id="main-form">
        <div class="col-12">
            <h1>Danh sách phòng trọ</h1>
        </div>
        <div class="col-12">
            <form class="form" action="${pageContext.request.contextPath}/home/search">
                <div class="form-control row">
                    <div class="col-8 text-start">
                        <input class="form-control" type="text" placeholder="Search" value="keyword"/>
                    </div>
                    <div class="col-4 text-end">
                        <button type="button" class="btn btn-primary" onclick="renderCreateForm()">Tạo mới</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-12">
            <table>
                <thead>
                <th>STT</th>
                <th>Mã phòng trọ</th>
                <th>Tên người thuê trọ</th>
                <th>Số điện thoại</th>
                <th>Ngày bắt đầu thuê</th>
                <th>Phương thức thanh toán</th>
                <th>Ghi chú</th>
                <th><input type="checkbox"/></th>
                </thead>
                <tbody>
                <c:forEach var="home" items="${dataList}">
                    <tr>
                        <td>${home.index}</td>
                        <td>${home.homeCode}</td>
                        <td>${home.customerName}</td>
                        <td>${home.phoneNumber}</td>
                        <td>${home.startDateStr}</td>
                        <td>${home.paymentMethodName}</td>
                        <td>${home.note}</td>
                        <td><input type="checkbox" name="select${index}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-12 text-end">
            <input type="button" class="btn" value="Xóa" onclick=""/>
        </div>
    </div>
    <div id="create-form" hidden>
        <form class="form form-control" action="${pageContext.request.contextPath}/home/create" method="post"
              onsubmit="return validateCreateHome()">
            <div class="col-12">
                <h1>Tạo thông tin thuê trọ
                </h1>
            </div>
            <div class="form-group col-12">
                <span id="validate-message"></span>
            </div>
            <div class="form-group col-12">
                <label for="inp-home-id">Mã phòng trọ</label>
                <input class="form-control" id="inp-home-id" type="text" name="homeId" value="" readonly/>
            </div>
            <div class="form-group col-12">
                <label for="inp-customer-name">Tên người thuê trọ</label>
                <input class="form-control" id="inp-customer-name" type="text" name="customerName" value=""/>
            </div>
            <div class="form-group col-12">
                <label for="inp-phone-number">Số điện thoại</label>
                <input class="form-control" id="inp-phone-number" type="text" name="phoneNumber" value=""
                       maxlength="10"/>
            </div>
            <div class="form-group col-12">
                <label for="inp-start-date">Ngày bắt đầu thuê</label>
                <input class="form-control" id="inp-start-date" type="date" name="startDate" value=""/>
            </div>
            <div class="form-group col-12">
                <label for="inp-payment-method">Hình thức thanh toán</label>
                <select class="form-control" id="inp-payment-method" name="paymentMethodId">
                    <c:forEach var="paymentMethod" items="${paymentMethodList}">
                        <option value="${paymentMethod.id}">${paymentMethod.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-12">
                <label for="inp-note">Ghi chú</label>
                <textarea class="form-control" id="inp-note" name="note" maxlength="200"></textarea>
            </div>
            <div class="form-group col-12">
                <input type="button" class="btn btn-secondary" value="Hủy" onclick="backToMain()">
                <input type="submit" class="btn btn-primary" value="Tạo mới"/>
            </div>
        </form>
    </div>
</div>
<script>
    function renderCreateForm() {
        document.getElementById("inp-home-id").value = "PT-001";
        document.getElementById("main-form").hidden = true;
        document.getElementById("create-form").hidden = false;
    }

    function backToMain() {
        document.getElementById("main-form").hidden = false;
        document.getElementById("create-form").hidden = true;
    }

    function validateCreateHome() {
        let inputCustomerName = document.getElementById("inp-customer-name").value
        if (inputCustomerName.length < 5 || inputCustomerName.length > 50) {
            document.getElementById("validate-message").innerHTML = "Tên người thuê trọ phải lớn hơn 5 và nhỏ hơn 50 ký tự!";
            return false;
        } else {
            const regex = /^[a-z]+$/;
            if (!regex.test(inputCustomerName)) {
                document.getElementById("validate-message").innerHTML = "Tên người thuê trọ không được chứa ký tự số!";
            }
        }
        let inputPhoneNumber = document.getElementById("inp-phone-number").value
        if (inputPhoneNumber.length !== 10) {
            document.getElementById("validate-message").innerHTML = "Số điện thoại phải 10 ký tự";
            return false;
        }
        let inputStartDate = document.getElementById("inp-start-date").value
        if (inputStartDate < Date.now()) {
            document.getElementById("validate-message").innerHTML = "Không được nhập ngày trong quá khứ";
            return false;
        }
        let inputNote = document.getElementById("inp-note").value
        if (inputStartDate < Date.now()) {
            document.getElementById("validate-message").innerHTML = "Không được nhập ngày trong quá khứ";
            return false;
        }


    }
</script>
</body>
</html>