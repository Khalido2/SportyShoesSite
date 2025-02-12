export async function fetchPayments() {
    try {
      const response = await fetch(`http://localhost:8091/payments/all`)

      if (response.ok) {
        return await response.json();
      }
    } catch (error) {
      console.error("Failed to fetch users")
    }

    return []
  }

  export async function fetchPaymentsByCategoryId(categoryId) {
    try {
      const response = await fetch(`http://localhost:8091/payments/byCategoryId/${categoryId}`)

      if (response.ok) {
        return await response.json();
      }
    } catch (error) {
      console.error("Failed to fetch users")
    }

    return []
  }