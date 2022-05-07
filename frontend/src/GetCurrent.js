

async function GetCurrent(setAuth, setUser, navigate) {
    return await fetch('http://localhost:8082/current', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: "include"
    })
        .then(response => {
            if (response === null) {
                alert('401 unauthorized')
                navigate('/')
            } else if (response.status !== 200) {
                alert(response.status)
                navigate('/')
            } else {
                return response.json()
            }
        })
        .then(data => {
            if (data.id === -1) {
                alert('401 unauthorized')
                navigate('/')
            } else {
                setAuth(true)
                setUser({id: data.id, type: data.type, restaurant: data.restaurant})
                return ({id: data.id, type: data.type, restaurant: data.restaurant})
            }
        })
}

export default GetCurrent;