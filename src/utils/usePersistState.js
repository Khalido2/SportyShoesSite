import { useMemo, useEffect, useState } from 'react';

export default function usePersistState (initial_value, id){
    const _initial_value = useMemo(() => {
        const local_storage_value = localStorage.getItem("state:" + id);

        if(local_storage_value){
            return JSON.parse(local_storage_value)
        }

        return initial_value;
    }, [])

    const [state, setState] = useState(_initial_value);

    //on change set in local storage
    useEffect(() => {
        const state_string = JSON.stringify(state);
        localStorage.setItem("state:"+ id, state_string)
    }, [state])

    return [state, setState];
}