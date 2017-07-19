import {EventEmitter} from 'events';

class HashStore extends EventEmitter {

    constructor() {
        super();
        this.data = {};
    }
	
	addValue(key, value) {
		this.data[key] = value;
		this.emit(key);
		this.persist();	
	}
	
	removeValue(key) {
	    delete(this.data[key]);
	    this.emit(key);
	    this.persist();
	}

	getValue(key) {
		return this.data[key];
	}
	
	persist() {
	    localStorage.setItem("hashStore", JSON.stringify(this.data));
	}
	
	recover() {
	    if (localStorage.getItem("hashStore") != null) {
	        this.data = JSON.parse(localStorage.getItem("hashStore"));
	    }
	}
};

const hashStore = new HashStore;
export default hashStore;