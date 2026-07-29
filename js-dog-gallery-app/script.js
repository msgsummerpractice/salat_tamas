document.getElementById('loadRandomDogButton').addEventListener('click', function() {
    const dogImage = document.createElement('img');
    fetch('https://dog.ceo/api/breeds/image/random')
        .then(response => response.json())
        .then(data => {
            dogImage.src = data.message;
            dogImage.alt = 'Random Dog';
            dogImage.width = 400;
            dogImage.height = 300;
            document.getElementById('imageContainer').innerHTML = '';
            document.getElementById('imageContainer').appendChild(dogImage);
        })
        .catch(error => {
            console.error('Error fetching dog image:', error);
        });
})