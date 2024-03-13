/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhlvd.util;

/**
 *
 * @author ThinhDuc
 */
public class ApplicationConstants {

    public class DispatchFeature {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateController";
        public static final String SHOW_ALL_PRODUCTS_CONTROLLER = "showAllProductController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
        public static final String VIEW_YOUR_CART_PAGE = "viewCart";
        public static final String REMOVE_ITEMS_FROM_CART_CONTROLLER = "removeItemFromCartController";
        public static final String CHECK_OUT_CART_CONTROLLER = "checkOutCartController";
        public static final String LOG_OUT_CONTROLLER = "logOutController";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String STARTUP_CONTROLLER = "startUpController";
    }

    public class LoginFeature {

        public static final String INVALID_PAGE = "invalidPage";
        public static final String LOGIN_PAGE = "loginPage";
        public static final String SEARCH_PAGE = "homePage";
    }

    public class SearchFeature {

        public static final String RESULT_PAGE = "homePage";
    }

    public class StartUpFeature {

        public static final String LOGIN_PAGE = "";
        public static final String RESULT_PAGE = "homePage";
    }

    public class ErrorFeature {

        public static final String ERROR_PAGE = "errorPage";
    }

    public class DeleteFeature {

        public static final String ERROR_PAGE = "errorPage";
    }

    public class GoShoppingFeature {

        public static final String PRODUCTS_PAGE = "productPage";
    }

    public class LogOutFeature {

        public static final String LOGIN_PAGE = "loginPage";
    }

    public class CreateAccountFeature {

        public static final String ERROR_PAGE = "errorCreateAccountPage";
        public static final String LOGIN_PAGE = "";
    }

    public class CheckOutFeature {

        public static final String ERROR_PAGE = "errorPage";
        public static final String SHOW_BILL_CONTROLLER = "showBillController";
    }
    
    public class GetBillFeature {

        public static final String ERROR_PAGE = "errorPage";
        public static final String BILL_PAGE = "showBillPage";
    }
}
