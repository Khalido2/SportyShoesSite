const SHOE_ROOT_PATH = "http://localhost:8091/shoes"

export async function addShoe(name, description, price, image, shoeCategory) {
    const formData = new FormData();

    formData.append("name", name);
    formData.append("description", description)
    formData.append("price", price)
    formData.append("image", image)
    formData.append("categoryId", shoeCategory)

    try {
      const response = await fetch(SHOE_ROOT_PATH+"/add", {method: "POST", body:formData});

      if(response.ok){
        return 1
      }
    } catch(error){
      return 0
    }
}

export async function fetchShoesInCategory(categoryId) {
    try {
        const response = await fetch(`http://localhost:8091/categories/shoes/${categoryId}`)

        if (response.ok) {
            const data = await response.json();
            return data
        }
    } catch (error) {
        console.error("Failed to fetch shoes from that category")  
    }

    return []
}

export async function fetchShoes() {
    try {
      const response = await fetch("http://localhost:8091/shoes/all");

      if (response.ok) {
        return await response.json();
      }else {
        console.error("Failed to fetch shoes");
      }
    } catch(error){
      console.error("Failed to fetch shoes");
    }

    return []
}

export function getShoeImagePath(imagePath){
    return `${SHOE_ROOT_PATH}/getImage/${imagePath}`
}