const imageSource = "https://dog.ceo/api/breeds/image/random";

const loadBtn = document.getElementById("load-button") as HTMLButtonElement;
const imageContainer = document.getElementById("image-container") as HTMLDivElement;
const loadingIndicator = document.getElementById("loading") as HTMLSpanElement;

function loadRandomDogImage(): void {
    imageContainer.innerHTML = "";
    imageContainer.appendChild(loadingIndicator);
    loadingIndicator.style.display = "inline";

    const img = new Image();
    fetch(imageSource)
        .then(response => response.json())
        .then(data => {
            img.src = data.message;
        })
        .catch(error => {
            console.error("Error fetching image:", error);
            loadingIndicator.style.display = "none";
            imageContainer.innerHTML = "<p style='color:red;'>Failed to fetch image. Please try again.</p>";
        });
    img.onload = () => {
        loadingIndicator.style.display = "none";
        imageContainer.innerHTML = "";
        imageContainer.appendChild(img);
    };

    img.onerror = () => {
        loadingIndicator.style.display = "none";
        imageContainer.innerHTML = "<p style='color:red;'>Failed to load image. Please try again.</p>";
    };
}

loadBtn.addEventListener("click", loadRandomDogImage);