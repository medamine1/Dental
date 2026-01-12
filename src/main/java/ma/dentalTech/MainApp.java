package ma.dentalTech;


import ma.dentalTech.conf.ApplicationContext;
import ma.dentalTech.mvc.controllers.modules.patient.api.PatientController;
import ma.dentalTech.mvc.ui.LoginView;
import ma.dentalTech.mvc.ui.DashboardView;

public class MainApp
{
    public static void main( String[] args )
    {
        LoginView.showLogin(DashboardView::showDashboard);
    }
}
