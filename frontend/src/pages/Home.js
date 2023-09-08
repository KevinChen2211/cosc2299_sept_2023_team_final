import React from "react";
import { useNavigate } from "react-router-dom";

function Home(props) {
  const navigate = useNavigate();
  const handleSubmit = (event) => {
   
      navigate("/signup");
      return;
    

  }
  return (
    <div className="text-center">
     {/* content describing website purpose */}
    
      <h1>SuperPrice</h1>
      <div className="form-group">
            
            </div>
            {props.email === null ?
              <>
              <p>this is the landing page</p>
                 <input onClick= {handleSubmit} className="btn btn-primary" value="Sign Up" />
                 <p></p><p>Already have an account?<a href="/login"> Login here</a></p>
              </>
              :
              <>
               <p>you only see this when you log in</p>

              </>
            }
    
    </div>
  );
}

export default Home;
