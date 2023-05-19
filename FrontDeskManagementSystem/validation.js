const form = document.getElementById('myForm');

form.addEventListener('submit', function(event) {
  // Get form fields
  const name = document.getElementById('name');
  const purpose = document.getElementById('purpose');
  const meetingPerson = document.getElementById('meetingPerson');
  const city = document.getElementById('city');
  const gender = document.getElementsByName('gender');
  const email = document.getElementById('email');
  const mobileNo = document.getElementById('mobileNo');

  // Validate form fields
  let isValid = true;

  if (name.value.trim() === '') {
    setErrorFor(name, 'Name cannot be blank');
    isValid = false;
  } else {
    setSuccessFor(name);
  }

  if (purpose.value.trim() === '') {
    setErrorFor(purpose, 'Purpose cannot be blank');
    isValid = false;
  } else {
    setSuccessFor(purpose);
  }

  if (meetingPerson.value.trim() === '') {
    setErrorFor(meetingPerson, 'Meeting person cannot be blank');
    isValid = false;
  } else {
    setSuccessFor(meetingPerson);
  }

  if (city.value.trim() === '') {
    setErrorFor(city, 'City cannot be blank');
    isValid = false;
  } else {
    setSuccessFor(city);
  }

  let genderSelected = false;
  for (let i = 0; i < gender.length; i++) {
    if (gender[i].checked) {
      genderSelected = true;
      setSuccessFor(gender[i]);
      break;
    }
  }
  if (!genderSelected) {
    setErrorFor(gender[0], 'Please select a gender');
    isValid = false;
  }

  if (email.value.trim() === '') {
    setErrorFor(email, 'Email cannot be blank');
    isValid = false;
  } else if (!isValidEmail(email.value.trim())) {
    setErrorFor(email, 'Email is not valid');
    isValid = false;
  } else {
    setSuccessFor(email);
  }

  if (mobileNo.value.trim() === '') {
    setErrorFor(mobileNo, 'Mobile number cannot be blank');
    isValid = false;
  } else if (!isValidMobileNo(mobileNo.value.trim())) {
    setErrorFor(mobileNo, 'Mobile number is not valid');
    isValid = false;
  } else {
    setSuccessFor(mobileNo);
  }

  // Prevent form submission if there are errors
  if (!isValid) {
    event.preventDefault();
  }
});

function setErrorFor(input, message) {
  const formGroup = input.parentElement;
  const error = formGroup.querySelector('.error-message');

  error.innerText = message;

  formGroup.classList.remove('success');
  formGroup.classList.add('error');
}

function setSuccessFor(input) {
  const formGroup = input.parentElement;

  formGroup.classList.remove('error');
  formGroup.classList.add('success');
}

function isValidEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email);
}

function isValidMobileNo(mobileNo) {
  const re = /^\d{10}$/;
  return re.test(mobileNo);
}
