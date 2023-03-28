import React, { useEffect, useState } from "react";
import { Col, Row } from "reactstrap";
import { loadAllPosts } from "../services/post-service";
import Post from "./Post";

const NewFeed = () => {

  const [postContent, setPostContent] = useState({
    content:[]
  })
  
  useEffect(() => {
    //load all the posts from the server
    loadAllPosts().then((data)=>{
        console.log(data)
        setPostContent(data)
    }).catch(error=>{
        console.log(error)
    })
  }, []);

  return (
        <div className="container-fluid" > 
            <Row>
                <Col md={{
                    size:10,
                    offset:1
                }} >
                <h1>Blog Count ({postContent?.totalElements}) </h1>
                {
                    postContent.content.map(post=><Post post={post} key={post.id}/>)
                }
                </Col>
                <Col>
                    <Post/>
                </Col>
            </Row>
        </div>
    );
};

export default NewFeed;
