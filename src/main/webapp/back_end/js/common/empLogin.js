async function EmpFunCheck(){
    const response = await fetch('/BuyGo/EmpLoginApi/back_end/EmpFunCheck', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json;charset:utf-8' },
})
    .then(resp => resp.json())
    .then(body => {
        const { empNo, empAcct, loginState, empfun } = body;
        // $("dropdown user user-menu").html(`
        //     <ul class="user-menu">
        //         <li><a href="/BuyGo/front_end/pages/member/myaccount.html">My Account</a></li>
        //         <li><a href="/BuyGo/front_end/pages/member/cart.html">My Wishlist</a></li>
        //         <li><a href="/BuyGo/front_end/pages/member/checkout.html">Checkout</a></li>
        //     </ul>
        //     `);
        // if (loginState) { // 如果已為登入狀態
        //     $("nav#tiny-menu > ul.user-menu").append(`<li id="logInAndLogOut"><a href="/BuyGo/api/back_end/EmpLogout">Log Out</a></li>`); // 修改登入登出按鈕為Log Out
        //     $("header#site-header").find("div.clearfix").html(`<strong style="font-weight: bold; position: absolute; right: 150px">您好, ` + empAcct + `</strong>`); // 顯示登入者帳號資訊
        //     let empDetail = [empNo, empAcct, empfun];
        //     return empDetail; // 回傳會員編號
        // }
        // else { // 如果為未登入狀態
        //     $("nav#tiny-menu > ul.user-menu").append(`<li id="logInAndLogOut"><a href="/BuyGo/back_end/pages/member/login/login_test.html">Log In</a></li>`); // 修改登入登出按鈕為Log In
        // }


    });
return response; // 將收到的會員編號回傳給呼叫方
}