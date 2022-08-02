function submitForm() {

    document.addEventListener('DOMContentLoaded', ()=> {

        const errorMsgEle = document.querySelector('#errorMessage') as HTMLDivElement;
        const successMsgEle = document.querySelector('#successMessage') as HTMLDivElement;
        errorMsgEle.style.display = 'none';
        successMsgEle.style.display = 'none';

        const countrySelectEle = document.querySelector("#countries");
        if(countrySelectEle !== null){
            console.log("Populating the countries dropdown.....");
            console.log("My additional logger...");
            fetch('/content/dam/anf-code-challenge/exercise-1/countries.json').then(response => {
                return response.json();
            }).then(data => {
                let output = "";
                Object.keys(data).forEach(function(key) {
                    output += `<option value="${data[key]}">${key}</option>`;
                })
                countrySelectEle.innerHTML = output;   
            }).catch(err => {
                console.log(err);
            })
        } 
    });

    if(document.getElementById('addUserFormBtn') !== null){
        document.getElementById('addUserFormBtn').addEventListener('click', (event) => {
            event.preventDefault();
            console.log("Inside form submit 123");
            const fName = document.getElementById('firstname') as HTMLInputElement;
            const lName = document.getElementById('lastname') as HTMLInputElement;
            const country = document.getElementById('countries') as HTMLSelectElement;
            const age = document.getElementById('age') as HTMLInputElement;
    
            if(fName.value !== null && lName.value !== null && country.value !== null && age.value !== null){
    
                fetch('/bin/saveUserDetails', {
                    method: 'POST',
                    headers: {
                        Accept: 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({firstName: fName.value, lastName: lName.value, age: age.value, country: country.value})
                })
                .then( res => res.json())
                .then( res => {
                    console.log(res);
                    const errorMsgEle = document.querySelector('#errorMessage') as HTMLDivElement;
                    const successMsgEle = document.querySelector('#successMessage') as HTMLDivElement;
    
                    if(res.status === "success"){
                        successMsgEle.style.display = "block";
                        successMsgEle.innerHTML = "User details saved successfully";
                        errorMsgEle.innerHTML = "";
                        errorMsgEle.style.display = "none";
                    }else if(res.status === "error"){
                        successMsgEle.style.display = "none";
                        successMsgEle.innerHTML = "";
                        errorMsgEle.innerHTML = res.message;
                        errorMsgEle.style.display = "block";
                    }
                }).catch (err => {
                    console.log(err);
                    const errorMsgEle = document.querySelector('#errorMessage') as HTMLDivElement;
                    errorMsgEle.style.display = "block";
                    errorMsgEle.innerHTML = "Exception with API endpoint, please try again.";
                });
    
            }else {
                const errorMsgEle = document.querySelector('#errorMessage') as HTMLDivElement;
                errorMsgEle.style.display = "block";
                errorMsgEle.innerHTML = "Please complete all fields before submission.";
            }
        });
    }    
};

export {submitForm};