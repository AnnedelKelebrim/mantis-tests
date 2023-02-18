package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase {

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "administrator");
        click(By.xpath("//input[@value='Login']"));
        type(By.name("password"), "root");
        click(By.xpath("//input[@value='Login']"));
    }

    public void goToManagePage() {
        click(By.cssSelector("a[href=\"/mantisbt-2.25.4/manage_overview_page.php\""));
    }

    public void goToManageUsers() {
        click(By.cssSelector("a[href=\"/mantisbt-2.25.4/manage_user_page.php\""));
    }

    private void goToUser() {
        click(By.xpath("//tr[2]/td/a"));
    }
    public void userPage() {
        loginAsAdmin();
        goToManagePage();
        goToManageUsers();
        goToUser();
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public String getUserName() {
        String user = wd.findElement(By.name("username")).getAttribute("value");
        return user;
    }

    public String getEmail() {
        String email = wd.findElement(By.name("email")).getAttribute("value");
        return email;
    }
}

