const CATEGORY_ROOT_PATH = "http://localhost:8091/categories"

export async function fetchCategories() {
    try {
        const response = await fetch(CATEGORY_ROOT_PATH+"/all")
  
        if (response.ok) {
          return await response.json();
        }
      } catch(error){
        console.error("Failed to fetch categories")
    }

    return []
}