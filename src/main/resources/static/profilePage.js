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

_e('Bud').onclick = function() {
    _e('profilePageInfo').innerHTML = `<h1 class="MinaAnnonser">Mina Budgivningar</h1>

                                               <div class="outer">
                                                   <div class="card" th:each="item : ${bidding}">
                                                       <h1 class="heading" th:text="${item.title}">
                                                           Rubrik
                                                       </h1>
                                                       <span class="badge rounded-pill bg-color" th:text="${item.keyWords}"></span>
                                                       <hr class="my-2">
                                                       <div class="img-container">
                                                           <img class="img"
                                                                th:src="'https://auction-photos-aw.s3.eu-west-2.amazonaws.com/' + ${item.pictureAddress}"/>
                                                       </div>
                                                       <hr class="my-2">
                                                       <p class="description" th:text="${item.getShortDescription()}">
                                                           Ingen beskrivning finns.
                                                       </p>
                                                       <button class="btn btn-primary btn-card detail" data-bs-toggle="modal"
                                                               data-bs-target="#myModal" th:id="${item.id}">Mer
                                                           information
                                                       </button>
                                                       <hr class="my-2">
                                                       <table>
                                                           <tbody>
                                                           <tr>
                                                               <td>Utgångspris:</td>
                                                               <td th:text="${item.startPrice}+' kr'"></td>
                                                           </tr>
                                                           <tr>
                                                               <td>Aktuellt bud:</td>
                                                               <td th:text="${item.getTopBid()}+' kr'">&nbsp;</td>
                                                           </tr>
                                                           </tbody>
                                                       </table>
                                                       <div class="auctionDiv">
                                                           <p hidden id="auctionEndDate" th:text="${item.endDateTime}"></p>
                                                           <span id="timeRemain"></span>
                                                       </div>
                                                       <hr class="my-2">
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



                                                       </div>
                                                   </div>
                                               </div>
                                           </div>`;
}

function _e(id) {
   return document.getElementById(id);
}


