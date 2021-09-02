
const countDownShort = () => {

    const auctionDivs = document.getElementsByClassName("auctionDiv")

        for(let element of auctionDivs){
            const stringDate = element.querySelector("#auctionEndDate").innerText
            const countDownDate = new Date(stringDate).getTime();

            // Get today's date and time
            const now = new Date().getTime();

            // Find the distance between now and the count down date
            let distance = countDownDate - now;

           if (distance < 0) {
              element.querySelector("#timeRemain").innerHTML = "Auktion är avslutad";
              continue;
           }

            // Time calculations for days, hours, minutes and seconds
            let days = Math.floor(distance / (1000 * 60 * 60 * 24));
            let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));

            // Display the result in the element with id="demo"

            if(days < 1 ){
                if(hours < 1) {
                    element.querySelector("#timeRemain").innerHTML ="mindre än 1"
                    +" timma kvar"
                }
                else {
                    element.querySelector("#timeRemain").innerHTML = hours + " timmar"
                    +" kvar"
                }
            } else {
                element.querySelector("#timeRemain").innerHTML = days + " dagar kvar"
            }

        }

}
countDownShort()
setInterval(countDownShort, 1000*60)