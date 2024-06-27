import React, {useState, useEffect} from "react";
import './ViewPollResults.css';
import Chart from "react-google-charts";

/**
 * Functional component responsible for displaying the poll results in a PieChart from google charts.
 */
const ViewPollResults = (props) => {
    /***
     * Function responsible for rendering tags for use in react methods.
     * @returns {JSX.Element}
     */
    return (
        <div className="piechart">
            <h2 key={props.title}>{props.title}</h2>
            <Chart
                width={'1500'}
                height={'300px'}
                chartType="PieChart"
                loader={<div>Loading Chart</div>}
                data={[
                    ['Choices', 'Number of votes'],
                    ...props.choicesCount
                ]}
                options={{
                    title: props.question,
                    width: 1500,
                    height: 850,
                    is3D: true
                }}
                rootProps={{ 'data-testid': '1' }}
            />
        </div>
    );
}

export default ViewPollResults;
