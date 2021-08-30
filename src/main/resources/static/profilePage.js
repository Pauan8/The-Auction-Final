_e('safetyButton').onclick = function() {
    _e('profilePageInfo').innerHTML = `<div class="container-profile">
                                    <h1 class="safetyText">Säkerhet</h1>
                                    <h2 class="profileFormText">Här kan du skapa ett nytt lösenord</h2>

                                    <div class="changePassword">
                                        <form class="formProfile" action="/passwordChange" method="POST">
                                            <label class="labelProfile" for="password">Nytt Lösenord: <input id="password" type="password" name="password" /></label>
                                            <label class="labelProfile" for="password2">Lösenord igen: <input id="password2" type="password" name="password2"/></label>
                                            <input  type="submit" value="Ändra">
                                        </form>
                                    </div>
                                </div>
                                </div>`;
}

function _e(id) {
   return document.getElementById(id);
}


_e('profilButton').onclick = function() {
    _e('profilePageInfo').innerHTML = `<div id="profilInställningar">
                                               <div class="outerProfile">
                                                   <div class="container-profile">
                                                       <div class="container-profile">
                                                           <h1 class="safetyText">Profilinställningar</h1>
                                                           <h2 class="profileFormText" style="padding-bottom:30px;">Här kan du ändra dina profilinställningar</h2>
                                                           <h3 class="profileFormText">Ändra din email</h3>

                                                           <div class="changePassword">
                                                               <form class="formProfile" action="/email" method="POST">
                                                                   <label class="labelProfile" for="email">Ny email: <input id="email" type="text"
                                                                                                                            name="email"/></label>
                                                                   <label class="labelProfile" for="email2">Ny email igen: <input id="email2" type="text"
                                                                                                                                  name="email2"/></label>
                                                                   <input type="submit" value="Ändra">
                                                               </form>
                                                           </div>

                                                           <div class="switch-Containers">
                                                               <p>Nyhetsbrev</p>
                                                               <label class="switch">
                                                                   <input type="checkbox">
                                                                   <span class="slider round"></span>
                                                               </label>
                                                               <p>Superkrafter</p>
                                                               <label class="switch">
                                                                   <input type="checkbox">
                                                                   <span class="slider round"></span>
                                                               </label>

                                                           </div>

                                                       </div>
                                                   </div>
                                               </div>
                                           </div>`;
}

function _e(id) {
   return document.getElementById(id);
}