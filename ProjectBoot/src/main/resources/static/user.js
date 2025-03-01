



document.addEventListener("DOMContentLoaded", function () {
        // Запрашиваем данные о пользователе через API

        fetch("/user/profile_user")
            .then(response => response.json())
            .then(user => {
                    // Заполняем поля на странице
                    document.getElementById("userId").textContent = user.id;
                    document.getElementById("username").textContent = user.username;
                    document.getElementById("surname").textContent = user.surname;
                    document.getElementById("age").textContent = user.age;
                    document.getElementById("email").textContent = user.email;
                    // Обработка ролей (если есть несколько)
                    let roles = user.roles.map(role => role.name).join(", ");
                    document.getElementById("roles").textContent = roles;
                    // Для отображения роли пользователя в навигации\
                    document.getElementById("navbarUserEmail").textContent = user.email;
                    document.getElementById("navbarUserRoles").textContent = roles;
                // Проверка, есть ли у пользователя роль ADMIN
                if (user.roles.some(role => role.name === "ADMIN")) {
                    // Если есть, показываем кнопку Admin
                    document.getElementById("adminButtonLi").style.display = "block";
                }

            })
            .catch(error => console.error("Error fetching user data:", error));


});
